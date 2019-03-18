# 功能
学习注解@ConfigurationProperties(prefix = "jlpay")
## 配置类
```$xslt
@Configuration
@Component
@ConfigurationProperties(prefix = "jlpay")
@Data
public class TestConf {
    private  Notify notify = new Notify();
    private  String node;

    public static TestConf TestConfStatic ;
    {
        TestConfStatic =this;
    }
    @Data
    public   class   Notify {
        String risk;
        String  trans;
    }


}

```
## 配置文件
```$xslt


jlpay:
  notify:
    risk: risk-url
    trans: trans-url
  node: node-url

```

# 测试
运行测试类
```$xslt

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NotifyServiceApplication.class})

public class TestConfTest {

    @Autowired
    public TestConf testConf;

    @Test
    public void test() {
        System.out.println(testConf.TestConfStatic.toString());

        System.out.println(testConf.toString());
    }
}
```
会看到控制台输出
```$xslt
TestConf(notify=TestConf.Notify(risk=risk-url, trans=trans-url), node=node-url)
TestConf(notify=TestConf.Notify(risk=risk-url, trans=trans-url), node=node-url)
```