## cinematograph
Учебный проект. jee.
JPA, REST, JSP
База данных по фильмам, актерам, тегам, комментариям к фильмам.
http api для редактирования данных, jsp для отображения данных
<br>
Для подключения к базе используется
   
    <jta-data-source>java:/cinematographDataSourse</jta-data-source>
    
т.е. должно быть предварительно настроено на СП JNDI cinematographDataSourse     

    После запуска приложения доступно
    
    http://localhost:8080/cinematograph/docs/ api через swagger-ui
    
    http://localhost:8080/cinematograph/hello hello страница
    http://localhost:8080/cinematograph/docs/ swagger ui
    http://localhost:8080/cinematograph/movie страница для поиска фильмов
    http://localhost:8080/cinematograph/actor страница для поиска актеров
    http://localhost:8080/cinematograph/tag страница для поиска тэгов
<br>
Тестировалось на wildFly11, PostgresSQL 10
<br>
https://wildfly.org/downloads/ 11.0.0.Final	2017-10-23	Java EE Full & Web Distribution

### Проведеная настройка:
1.установка postgre по умолчанию. создание бд cinematograph
<br>
2.в папке

    wildfly-11.0.0.Final\modules\system\layers\base\org     

создать \postgresql\main и разместить по wildfly-11.0.0.Final\modules\system\layers\base\org\postgresql\main
module.xml, postgresql-42.2.9.jar,
<br>
где
module.xml
    
    <module xmlns="urn:jboss:module:1.5" name="org.postgresql">
        <resources>
             <resource-root path="postgresql-42.2.9.jar" />
        </resources>
         <dependencies>
            <module name="javax.api"/>
            <module name="javax.transaction.api"/>
        </dependencies>
     </module>
<br>     
3.Внести изменения в файл wildfly-11.0.0.Final\standalone\configuration\standalone.xml
добавить (указать соответствующие логин/пароль и порт в jdbc строке) в datasources

    <datasource jta="true" jndi-name="java:/cinematographDataSourse" pool-name="cinematographDataSourse" enabled="true" use-ccm="false">
       <connection-url>jdbc:postgresql://localhost:5432/cinematograph</connection-url>
       <driver-class>org.postgresql.Driver</driver-class>
       <driver>postgres</driver>
           <security>
               <user-name>postgres</user-name>
               <password>root</password>
           </security>
           <validation>
               <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
               <background-validation>true</background-validation>
               <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
            </validation>
     </datasource>
                
добавить в datasources - > drivers

                   name="postgres" module="org.postgresql">
                        <driver-class>org.postgresql.Driver</driver-class>
                    </driver>
      
 <br>                   
 4. wildfly-11.0.0.Final\bin\standalone.bat
 <br>
 http://localhost:9990/console/App.html#standalone-deployments деплоймент приложения
<br>
папка wildFly содержит примеры
