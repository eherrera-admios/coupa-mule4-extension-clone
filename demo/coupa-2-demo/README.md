# Coupa Connector Demo

### Pre-requisites

1. Coupa API Key
2. Mule Server 4.1.X EE Runtime
3. Anypoint Studio 7

### Preparation

* Import this DEMO in Anypoint Studio 7 by going to **File** ? **Import...** ? **Anypoint Studio project from File system** ?
Select coupa-2-demo as **Project Root**, set the **Project Name** and select the **Server Runtime** version ? **Finish**
* Once imported, in the **src/main/resources/configuration.yaml** file, add your instance full url and api key in the placeholders for the required configuration parameters. Populate them in order to make the DEMO work 
```yaml
coupa:
  instance: "https://example.example.com"
  apikey: "4d30ec4d024e284596fb7f9b7b84cedb2f50b3ed" 
```

### Usage
* In **Anypoint Studio**, Right click in the project folder ? **Run As** ? **Mule Application**.
* If the Mule App is deployed correctly, you can start testing the flows by calling this endpoints:
    * *localhost:8081/getAddress?id={}* - This flow will show the address with the id defined as query parameter
    * *localhost:8081/updateUser?id={}&lastname={}* - This flow will update the last name of user with the id defined as query parameter
    * *localhost:8081/createExpenseReport?title={}* - This flow will create new draft expense report with the title defined as query parameter
    * *localhost:8081/createExpenseReportAndMarkExported?title={}* This flow will create new draft expense report with the title defined as query parameter and then mark it as exported
    * *localhost:8081/getRejectedApprovals* This flow will list all the rejected approvals