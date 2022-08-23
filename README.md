# PlaywrightCucumber

Playwright and Cucumber

Here you could see the example of project build with Playwright testing tool and Cucumber framework.

To see an example of usage - switch to the 'Examples' branch

Doc:

BrowserInstance: Playwright wrapper implemented with Singleton pattern. The Browser settings adjusting here. Set Context Options and Launch Options in methods 'getLaunchOptions' and     'getContextOptions'. To initiate Playwright call method 'initiate'.

StepDef: StepDef should extend from class BaseStep which has Initialized BrowserInstance and methods e.g. getPage.

POM: Can be implemented in two ways. Create BasePage with field Page and extend from it. So the pages will contain methods to interact with the webpage. Or create Page class as a locator storage with getters to get or generate dynamic locators. In this case all interactions with the web page will be covered in the steps directly.

Model: A Model class is a main class for model's to create hierarchy.

BaseRow and @ModelInfo: To handle tables, list of elements on the page or Cucumber DataTable there a BaseRow class and ModelInfo annotation. Row for a model should extend from BaseRow class and fields inside should be annotated with the ModelInfo annotation. In the annotation in 'name' put the column name or item identifier (Switch to 'Examples' branch to see the example of usage. https://github.com/IllarionarSem/PlaywrightCucumber/tree/Examples). Override method asModel in Row class to parse data.

ModelHandler: Util class which returns a list of Models from the Page or from the Cucumber DataTable. Switch to the branch 'Example' to see the example of usage (https://github.com/IllarionarSem/PlaywrightCucumber/tree/Examples).

ConfigReader: Util to read properties.

DataStorage: Util to share data between steps.

JsonUtil: Util to parse Json file into Model, or to string.

See the example of usage on the branch 'Examples' https://github.com/IllarionarSem/PlaywrightCucumber/tree/Examples