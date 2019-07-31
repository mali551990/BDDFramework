package Runner;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/main/java/features/",
		glue="StepDefinition",
		tags= {"@APITest"},
		plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json" }
		)

public class executeRunner 
{
	

}
