package com.pankaj.nace.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
//import org.springframework.util.StringUtils;

import com.pankaj.nace.dto.Nace;
import com.pankaj.nace.listener.JobCompletionNotificationListener;
import com.pankaj.nace.processor.NaceItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<Nace> reader() {
		FlatFileItemReader<Nace> reader = new FlatFileItemReaderBuilder<Nace>().name("naceItemReader")
				.resource(new ClassPathResource("NACE_REV2_20220710_191348.csv")).delimited()
				.names(new String[] { "orderId", "level", "code", "parent", "description", "thisItemIncludes",
						"thisItemAlsoIncludes", "rulings", "thisItemExcludes", "referenceToIsicRev4" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Nace>() {
					{
						setTargetType(Nace.class);
					}
				}).linesToSkip(1).build();

//		reader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy() {
//		    private static final String CONTINUATION = "\\";
//
//		    private String continuation = CONTINUATION;
//		    private final String delimiter =",";
//		    @Override
//		    public boolean isEndOfRecord(String line) {
//		        return true;
//		    }
//
//		    private boolean isQuoteUnterminated(String line) {
//		        return false;
//		    }
//
//		    private boolean isContinued(String line) {
//		        if (line == null) {
//		            return false;
//		        }
//		        return true;
//		    }
//		});
		return reader;
	}

	@Bean
	public NaceItemProcessor processor() {
		return new NaceItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Nace> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Nace>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO nace (order_id, level, code, parent, description, this_item_includes, this_item_also_includes, rulings, this_item_excludes, reference_to_isic_rev4) "
						+ "VALUES (:orderId, :level, :code, :parent, :description, :thisItemIncludes, :thisItemAlsoIncludes, :rulings, :thisItemExcludes, :referenceToIsicRev4)")
				.dataSource(dataSource).build();
	}

	@Bean
	public Job importNACEJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importNACEJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				.end().build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Nace> writer) {
		return stepBuilderFactory.get("step1").<Nace, Nace>chunk(10).reader(reader()).processor(processor())
				.writer(writer).build();
	}
}
