**IDEA创建文件自动注释:**

`File -> Settings -> Editor -> File and Code Templates -> Class/Interface/Enum`

```java
/**
  *@Author: ${USER}
  *@Date: ${DATE} ${TIME}
  *@Description:
**/
```

### 1、Spring JPA注解

用Spring JPA(Jpa)，实体类中要使用注解`@Entity`,`@Data`,主键`id`要使用注解`@Id`，如果要设置为ID自增一定要加上注解`@GeneratedValue(strategy = GenerationType.IDENTITY)`。

> **注意：** 如果实体类中有时间属性，且定义了默认的时间，自动更新，如果在功能实现过程中要更新实体类中的某几个属性，不包括时间，则时间不会更新，此时需要设置为时间随更改而更新，则需要在实体类上加注解`@DynamicUpdate`，从而可以达到时间动态更新。

### 2、数据库约束

在数据库中插入数据的时候，如果不想加入相同的数据，比如类目表中的类目编号，不希望插入两个相同的编号，此时可以给类目编号设置约束，语法是：  

```mysql
unique key `uqe_category_type` (`category_type`) 
```

### 3、事物处理

如果不想把测试数据加入到数据库中，可以在测试方法上加注解`@Transactional`进行事务处理，事情做完之后进行回滚。 

### 4、前台接口返回格式

在前台返回的接口格式中，属性名称如果与类中对应的名称不一致，可以加注解`@JsonProperty`解决。

```Java
// id为前台需要的字段名称，productId为类中定义的属性名称
@JsonProperty("id")
private String productId; 
```

### 5、Arrays.asList(Value)使用

测试数组的时候可以使用如下方式：

```
 productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
```

### 6、随机生成唯一的主键工具类

代码实现：

```java
import java.util.Random;
 /**
     * 生成唯一的主键
     * 格式：时间+随机数
     */
public class KeyUtil {
    //多线程可能会重复，添加synchronized关键字
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        //随机生成6位随机数
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
```

### 7、对象拷贝

对象拷贝必须保证两个对象的字段名一致，还应该注意对象拷贝时应该先把改变的值存入拷贝对象中，再进行对象拷贝。

```java
BeanUtils.copyProperties(拷贝对象, 目标对象);
```

### 8、`gson`使用

在`json`接口数据类型中，如果想要把`String`转换成`List`类型，可以使用`gson`。

* 首先需要引入`maven`依赖包：

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
</dependency>
```

* 在需要转化的类中使用`gson`进行转换：

```java
Gson gson = new Gson();
List<OrderDetail> orderDetailList = new ArrayList<>();
try {
        // orderForm中的items为String类型，要转化为List类型
        orderDetailList = gson.fromJson(orderForm.getItems(), 
        new TypeToken<List<OrderDetail>>() {}.getType());
        } catch (Exception e) {
            log.error("[对象转换] 错误， String={}", orderForm.getItems());
        }
```

* 参数格式：

```java
items: [{
    productId: "1423113435324",
    productQuantity: 2 //购买数量
}]
```

### 9、`java8`lamada表达式的使用

```java
List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
```

### 10、与前台交互的字段校验

```java
@NotEmpty(message = "买家姓名必填")
private String name;
```

### 11、日期格式转换工具类(时间戳）

* 首先编写工具类：

```java
public class DateToLongSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime() / 1000);
    }
}
```

* 然后在要修改的类的时间字段上边加注解：

```java
/** 创建时间. */
@JsonSerialize(using = DateToLongSerializer.class)
private Date createTime;
```

12、`@JsonInclude`使用

当接口中返回的数据中有字段为null时，则不显示该字段，可以在要拒绝显示值为空的类上加注解实现：

```java
@JsonInclude(JsonInclude.Include.NON_NULL)
```

如果想要所有值为空的字段都不显示，则可以在`application.xml`中全局配置：

```java
# 此处配置为全局的，只要返回的对象为null，则不显示
spring:
  jackson:
    default-property-inclusion: non_null
```

### 13、微信支付

**一、网页授权**

（API文档：`https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html#0`）

可以申请测试号进行测试：
`https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Requesting_an_API_Test_Account.html`

配置网页授权回调域名：

![](https://tva1.sinaimg.cn/large/00831rSTly1gdir0km6d3j31ba0u0q7u.jpg)

（1）用户同意授权，获取code

```
// scope为snsapi_base (不弹出授权页面，直接跳转，只能获取用户openid)
https://open.weixin.qq.com/connect/oauth2/authorize?appid=自己的appID&redirect_uri=http://localhost:8080/sell/wexin/auth&response_type=code&scope=snsapi_base&state=123#wechat_redirect

// scope为snsapi_userinfo(弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息)
https://open.weixin.qq.com/connect/oauth2/authorize?appid=自己的appID&redirect_uri=http://localhost:8080/sell/wexin/auth&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect
```

如果用户同意授权，页面将跳转至 

```
http://localhost:8080/sell/wexin/auth/?code=获取到的code
```

（2）通过code换取网页授权access_token

```
https://api.weixin.qq.com/sns/oauth2/access_token?appid=自己的APPID&secret=公众号的appsecret&code=上一步获取的code&grant_type=authorization_code
```

授权成功返回`json`数据包：

```json
{
  "access_token":"ACCESS_TOKEN",
  "expires_in":7200,
  "refresh_token":"REFRESH_TOKEN",
  "openid":"OPENID",
  "scope":"SCOPE" 
}
```

（3）代码编写:获取openid

```java
/** 买家微信授权获取openID */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
       // 1、配置
       // 2、调用方法
        String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, "utf-8"));
        log.info("[微信网页授权] 获取code, result = {}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
```

获取结果展示：

```
2019-11-23 23:45:26,285 - 进入auth方法...
2019-11-23 23:45:26,286 - code=071CiVZa09BRTx1gHZYa0fiDZa0CiVZk
2019-11-23 23:45:26,610 -
response={
"access_token":"27_qv9KjpVpBw-KXneKPTl7mMk3wkAW-tLWjCEGZJOENNmb67H-7CzWd3pyDg94WyBmKWaM_Sy6vPM796pJPdnRYiSS_6cNjagSdGCQBgrUfA0",
"expires_in":7200,
"refresh_token":"27_5ETuxubgACyTbAzItUs--NRyHU-j-_f5SeJrx65vYTddgOm54OmNCNL-xp9u37kj8vn98kfiuyrCuzGzQGqGIoYPOAlOvCyjX1exvdLNmlw",
"openid":"o37zu07P4j_zpb9_qQhmeuEwPwc0",
"scope":"snsapi_userinfo"}
```

![](https://tva1.sinaimg.cn/large/00831rSTly1gdir0qm8qqj31we0ean22.jpg)

**二、微信订单支付**

发起支付、异步通知、微信退款

微信支付sdk:
`https://github.com/Pay-Group/best-pay-sdk`

（1）两数比较的工具类
    

```java
    public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}
```

**三、内网穿透工具**

（1）进行域名的获取（`https://natapp.cn/`）：

![](https://tva1.sinaimg.cn/large/00831rSTly1gdir0wmy9lj327y0qm4qp.jpg)

（2）启动`natapp`,把其分配的域名映射到本地的地址：

```
cd /Users/cyh/Documents/software 
./natapp -authtoken=b59a864636737ad6
```

![](https://tva1.sinaimg.cn/large/00831rSTly1gdir11m07wj30vo0kadlk.jpg)

（3）对端口进行测试：
`http://ehsqrc.natappfree.cc/sell/buyer/product/list`

![](https://tva1.sinaimg.cn/large/00831rSTly1gdir15z7csj31ja0u0n6x.jpg)

### 14、枚举问题

我们一般在设计枚举类是在数据库中存的数字编码，然后对应到枚举状态，而当我们需要在页面显示的时候，往往是需要返回编码对应的信息的，因此，需要编写枚举工具类进行转换。

枚举工具类：

```java
public interface CodeEnum {
    Integer getCode();
}
```

```java
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
```

```java
@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String message;

    //构造方法
    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
```

然后在对应用到的枚举实体类（`ProductInfo.java`）中添加方法：

```java
@JsonIgnore  // 表示忽略该方法
public ProductStatusEnum getProductStatusEnum() {
    return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
}
```

### 15、项目刷新

如果前端使用freemarker模版，若想当模版中的信息修改后，不需要重新启动项目，只需要进行`Build Project`就可以刷新。

![](https://tva1.sinaimg.cn/large/00831rSTly1gdir1c0toaj327g0d2na6.jpg)

### 16、微信模版推送消息

> **小技巧**：一般在业务逻辑中编写代码呢，像优先级或者重要程度一般的消息逻辑在判断时如果有错误，一般打印错误日志即可，不需要抛出异常，因为这种消息模块逻辑是要加在某块重要的业务逻辑代码中的，而且该代码会涉及到事务处理，如果消息逻辑抛出异常的化则重要的业务逻辑代码不能继续执行，将会回滚，这样会影响主要的业务逻辑。

代码示例：

```java
/**
 * @Author: cyh
 * @Date: 2019-11-24 14:50
 * @Description: 微信推送模版消息
 **/
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WeChatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲，请记得收货。"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "18868812345"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5", "￥" + orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临！")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e) {
            log.error("【微信模版消息】发送失败, {}", e);
        }
    }
}
```

### 17、卖家端扫码登录开发

微信开放平台微信登录文档:

```
https://developers.weixin.qq.com/doc/oplatform/Website_App/WeChat_Login/Wechat_Login.html
```

### 18、微信登录AOP身份认证

微信登录时进行AOP实现身份认证以及登录登出功能实现。

### 19、webSocket消息推送

### 20、异常捕获

创建订单时商品不存在的异常：

![](https://tva1.sinaimg.cn/large/00831rSTly1gdir2plhjfj31il0u0dlu.jpg)

在创建订单的时候，如果加入的商品不存在，则会报出异常，但此时想要让异常打印出一定规范的json格式，同商品添加成功时格式一致，并且也可以设置状态码，则可以添加代码如下：

```java
@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(value = SellException.class)  // 异常提示
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)  //设置自己想要返回的状态码
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
```

最终显示效果：

创建订单成功时的接口返回数据格式：

![](https://tva1.sinaimg.cn/large/00831rSTly1gdir32oj9rj31if0u0q8e.jpg)

同订单成功返回接口格式一致的异常捕获：

![](https://tva1.sinaimg.cn/large/00831rSTly1gdir3akghhj31l90u0dl0.jpg)
![](https://user-gold-cdn.xitu.io/2019/11/12/16e5f4bc437fa3a7?w=2182&h=640&f=png&s=101158)

### 21、JPA和Mybatis选择

（1）建表用sql,不用jpa建表

（2）慎用`@OneToMany`和`@ManyToOne`,表与表之间的关系最好在建库时加上。

### 22、mybatis注解使用

* 引入依赖：

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>
```

* 在启动类中加入注解：

```java
@MapperScan(basePackages = "对应的mapper文件包")
```

* 在mapper中编写接口代码：

```java
public interface CategoryMapper {

    // 添加
    /** 传map参数 */
    @Insert("insert into product_category (category_name, category_type) values (#{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    /** 使用对象参数 */
    @Insert("insert into product_category (category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);
    
    //查询
    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType"),
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType"),
    })
    List<ProductCategory> findByCategoryName(Integer categoryName);
    
    // 更新
    /** 根据参数更新 */
    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName, 
                             @Param("categoryType") Integer categoryType);

    /** 根据对象更新 */
    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);
    
    // 删除
    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);
}

```

如果想要查看mapper中的sql语句，可以在`application.yml`中进行配置：

```yml
logging:
  level:
    # com.edu.cn.dishsell.mapper为mapper对应的包名
    com.edu.cn.dishsell.mapper: trace
```

日志打印结果：
![](https://tva1.sinaimg.cn/large/00831rSTly1gdir413npuj31y10u0ti3.jpg)

### 23、用压测模拟并发的简易工具`Apache ab`

使用方法：

```xml
// -n 100 表示发送100个请求，-c 100 表示模拟100个并发
ab -n 100 -c 100 http://www.baidu.com/

//-t 60 表示连续60秒发送请求，-c 100 表示模拟100个并发
ab -t 60 -c 100 http://www.baidu.com/

```

### 24、处理并发情况

* 使用`synchronized`处理并发

缺点：

（1）无法做到细粒度控制

（2）只适合单点的情况

* #### `redis`分布式锁

（1）查看官网`redis.io`： SETNX GETSET

（2）把需要进行并发测试的代码进行加锁和减锁

（3）代码编写：

```java
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     *
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        // 如果锁过期
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            // 获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (! StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("[redis分布式锁] 解锁异常，{}", e);
        }

    }
}

```

（4）特点：

* 支持分布式
* 可以更细粒度的控制
* 多台机器上多个进程对一个数据进行操作的互斥

### 25、Redis缓存

特点：命中、失效、更新

代码编写：

（1）在启动类加注解`@EnableCaching`

（2）在需要添加缓存的接口上添加注解（注意：需要对用到的类进行序列化`Serializable`）

```java
// 查询不更改数据时缓存注解
@Cacheable(cacheNames = "product", key = "123")

// 修改数据时缓存注解
@CachePut(cacheNames = "product", key = "123")

// 清除缓存并添加或修改 key默认所对应的是接口中所对应的参数
@CacheEvict(cacheNames = "product", key = "123")

// 可以配置全局的cacheNames
@CacheConfig(cacheNames = "product")
@Cacheable(key = "123")

// key动态获取
@Cacheable(cacheNames = "product", key = "123", condition = "#id.length() > 3", unless = "#result.getCode() != 0")
public ResultVO list(@RequestParam("id") String id)

```

技巧：下载插件（`GenerateSerialVersionUID`）并设置快捷键：

```java
// 使用快捷键control + command + i
    private static final long serialVersionUID = 5404115822959335572L;
```

![](https://tva1.sinaimg.cn/large/00831rSTly1gdir52spo3j316q0u0aek.jpg)
