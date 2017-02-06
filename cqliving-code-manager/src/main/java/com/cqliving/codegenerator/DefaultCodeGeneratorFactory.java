package com.cqliving.codegenerator;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.cqliving.codegenerator.db.dialect.MetadataLoadDialect;
import com.cqliving.codegenerator.db.metadata.TableMetadata;
import com.cqliving.codegenerator.module.ModuleGenerator;
import com.cqliving.codegenerator.parser.NamesHold;
import com.cqliving.codegenerator.parser.NamesResolver;

public class DefaultCodeGeneratorFactory implements CodeGeneratorFactory, ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(DefaultCodeGeneratorFactory.class);

	private GenerateConfiguration generateConfiguration;

	private MetadataLoadDialect metadataLoadDialect;

	private NamesResolver namesResolver;

	private ApplicationContext applicationContext;

	@Override
	public void generateTable(String database, String tableName) {
		try {
			GenerateContext generateContext = loadGenerateContext(database, tableName);
			Map<String, ModuleGenerator> moduleGeneratorMaps = applicationContext.getBeansOfType(ModuleGenerator.class);
			logger.debug("Find reigstered ModuleGenerator: " + moduleGeneratorMaps.size() + " --> " + moduleGeneratorMaps);
			for (Map.Entry<String, ModuleGenerator> entry : moduleGeneratorMaps.entrySet()) {
                if(generateConfiguration.isGenerate(entry.getKey())){
				    entry.getValue().generate(generateContext);
                }
			}
			logger.info("Generate from [" + tableName + "] to Code/Resource/Pages success.");
		} catch (Exception e) {
			logger.error("Generate Table fail. --> tableName: " + tableName, e);
		}

	}

	@Override
	public void generateTables(String database, String... tableNames) {
		for (String tableName : tableNames) {
			generateTable(database, tableName);
		}
	}

	protected GenerateContext loadGenerateContext(String database, String tableName) {
		MetadataLoadDialect dialect = getMetadataLoadDialect();
		TableMetadata tableMetadata = dialect.loadTableMetadata(database, tableName);
		String entityIdDeclare = getMetadataLoadDialect().getEntityIdDeclare(tableName);
		NamesHold namesHold = namesResolver.resolve(tableName);
		GenerateContext generateContext = new GenerateContext();
		logger.debug("Configurations:\n"+getGenerateConfiguration());
		generateContext.setConfiguration(getGenerateConfiguration());
		generateContext.setTable(tableMetadata);
		generateContext.setEntityIdDeclare(entityIdDeclare);
		generateContext.setNames(namesHold);
		return generateContext;
	}

	public GenerateConfiguration getGenerateConfiguration() {
		return generateConfiguration;
	}

	public MetadataLoadDialect getMetadataLoadDialect() {
		return metadataLoadDialect;
	}

	public void setMetadataLoadDialect(MetadataLoadDialect metadataLoadDialect) {
		this.metadataLoadDialect = metadataLoadDialect;
	}

	public void setGenerateConfiguration(GenerateConfiguration generateConfiguration) {
		this.generateConfiguration = generateConfiguration;
	}

	public NamesResolver getNamesResolver() {
		return namesResolver;
	}

	public void setNamesResolver(NamesResolver namesResolver) {
		this.namesResolver = namesResolver;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
