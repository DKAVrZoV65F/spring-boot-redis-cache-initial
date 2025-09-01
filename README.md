# 🚀 Spring Boot Redis Cache Example

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0+-6DB33F?style=for-the-badge&logo=springboot)
![Redis](https://img.shields.io/badge/Redis-7.4.2-DC382D?style=for-the-badge&logo=redis)
![H2 Database](https://img.shields.io/badge/H2-Database-0040FF?style=for-the-badge&logo=h2)

</div>

Проект демонстрирует использование Redis для кэширования в Spring Boot приложении с полным набором CRUD операций. Включает интеграционные тесты с Testcontainers.

## ✨ Особенности

- ✅ Полный CRUD для продуктов
- ⚡ Кэширование с помощью Redis
- 🧪 Интеграционные тесты с Testcontainers
- 🎯 Валидация данных
- 📦 Сериализация JSON в Redis
- 🐳 Готовность к работе с Docker

## 🛠 Технологии

- **Java 21**
- **Spring Boot 3.0+**
- **Spring Data Redis**
- **H2 Database** (in-memory)
- **Testcontainers**
- **Jackson** для сериализации
- **Maven**

## 📦 Зависимости

Основные зависимости в `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 🚀 Запуск приложения

1. **Запустите Redis**:
```bash
docker run -d --name redis -p 6379:6379 redis:7.4.2
```

2. **Соберите и запустите приложение**:
```bash
mvn clean spring-boot:run
```

Приложение будет доступно по адресу: `http://localhost:8080`

## 📡 API Endpoints

| Метод | Эндпоинт | Описание |
|-------|----------|----------|
| `POST` | `/api/product` | Создать новый продукт 📝 |
| `GET` | `/api/product/{id}` | Получить продукт по ID 📦 |
| `PUT` | `/api/product` | Обновить продукт 🔄 |
| `DELETE` | `/api/product/{id}` | Удалить продукт ❌ |

## 🔄 Примеры запросов

**Создание продукта**:
```bash
curl -X POST -H "Content-Type: application/json" \
-d '{"name":"MacBook Pro", "price": 2499.99}' \
http://localhost:8080/api/product
```

**Получение продукта**:
```bash
curl http://localhost:8080/api/product/1
```

## ⚙️ Конфигурация Redis

```java
@Bean
public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMinutes(10))
        .serializeValuesWith(RedisSerializationContext.SerializationPair
            .fromSerializer(new Jackson2JsonRedisSerializer<>(ProductDto.class)));
    
    return RedisCacheManager.builder(connectionFactory)
        .cacheDefaults(config)
        .build();
}
```

## 🧪 Тестирование

Запустите тесты с помощью:
```bash
mvn test
```

Тесты используют Testcontainers для поднятия Redis контейнера. Проверяют:
- Создание и кэширование продукта 🧩
- Чтение из кэша ⚡
- Обновление и инвалидацию кэша 🔄
- Удаление и очистку кэжа ❌

## 📁 Структура проекта

```
src/
├── main/
│   ├── java/
│   │   └── com/techie/springbootrediscache/
│   │       ├── config/RedisConfig.java
│   │       ├── controller/ProductController.java
│   │       ├── dto/ProductDto.java
│   │       ├── entity/Product.java
│   │       ├── repository/ProductRepository.java
│   │       ├── service/ProductService.java
│   │       └── SpringBootRedisCacheApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── SpringBootRedisCacheApplicationTests.java
```

## 🔧 Настройки приложения

Основные настройки в `application.properties`:
```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.cache.type=redis
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.show-sql=true
```

---

## 📄 Лицензия

Этот проект создан в учебных целях.
