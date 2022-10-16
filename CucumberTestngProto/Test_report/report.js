$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/FeatureFiles/Search_Wikipedia_TestNg.feature");
formatter.feature({
  "line": 2,
  "name": "Search_Wikipedia",
  "description": "",
  "id": "search-wikipedia",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Module_Login"
    }
  ]
});
formatter.before({
  "duration": 1253441,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "TC01_Search_in_Google",
  "description": "",
  "id": "search-wikipedia;tc01-search-in-google",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@UI"
    },
    {
      "line": 4,
      "name": "@Mobile"
    },
    {
      "line": 4,
      "name": "@Automated"
    },
    {
      "line": 4,
      "name": "@Regression"
    },
    {
      "line": 4,
      "name": "@Priority_High"
    },
    {
      "line": 4,
      "name": "@Login_Error"
    }
  ]
});
formatter.step({
  "comments": [
    {
      "line": 6,
      "value": "#Check First Automation year"
    }
  ],
  "line": 7,
  "name": "I start a \"chrome\" sesion and debug environment is set to \"Yes\"",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I wait for \"Home_Google\" screen to load",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "I tap on \"Desktop_search_bar\"",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "I write the item name \"automatización\"",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "I look for the wikipedia entry",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "I wait for \"Wikipedia\" screen to load",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "I look for the year of the first automation process",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "I take one screenshot",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "chrome",
      "offset": 11
    },
    {
      "val": "Yes",
      "offset": 59
    }
  ],
  "location": "CWB_Definitions.startApp(String,String)"
});
formatter.result({
  "duration": 300450108,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Home_Google",
      "offset": 12
    }
  ],
  "location": "CWB_Definitions.waitForElement(String)"
});
formatter.result({
  "duration": 4357714900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Desktop_search_bar",
      "offset": 10
    }
  ],
  "location": "CWB_Definitions.selectBoB(String)"
});
formatter.result({
  "duration": 106402075,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "automatización",
      "offset": 23
    }
  ],
  "location": "CWB_Definitions.setStrToSearch(String)"
});
formatter.result({
  "duration": 3339064793,
  "status": "passed"
});
formatter.match({
  "location": "CWB_Definitions.wikipediaEntry()"
});
formatter.result({
  "duration": 1125507263,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Wikipedia",
      "offset": 12
    }
  ],
  "location": "CWB_Definitions.waitForElement(String)"
});
formatter.result({
  "duration": 91617250,
  "status": "passed"
});
formatter.match({
  "location": "CWB_Definitions.historiaTemprana()"
});
formatter.result({
  "duration": 243483090,
  "status": "passed"
});
formatter.match({
  "location": "CWB_Definitions.pageScreenshot()"
});
formatter.embedding("image/png", "embedded0.png");
formatter.result({
  "duration": 262251044,
  "status": "passed"
});
});