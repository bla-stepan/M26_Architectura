package architecture.configuration;

public class DatabaseConfiguration {

    public void configure(){
        if("IN_MEMORY".equals(System.getProperty("DB"))){
            //Если задана эта конфигурация, то приложение не будет подключаться в реальной БД
        }else if ("REAL_DB".equals(System.getProperty("DB"))){
            //В этом случае приложение должно  будет подключиться к реальной БД
        }
    }
}
