

![GitHub last commit (branch)](https://img.shields.io/github/last-commit/opengoofy/hippo4j/develop?color=orange)

## LettyLink


## Project Overview 📖
![](https://images-machen.oss-cn-beijing.aliyuncs.com/image-20231115133642504.png)

A short link is a URL (Uniform Resource Locator) that has been transformed from a long, original URL into a shorter, more memorable URL using a specific algorithm or service. Short links typically consist of only a few characters, whereas the original long URL can be very lengthy.

The principle of short links is quite simple: a long URL is transformed into a shorter, more manageable URL, which then redirects to the original long URL. The process involves the following steps:

1. **Generate a Unique Identifier**: When a user inputs or submits a long URL, the short link service generates a unique identifier or short code.
2. **Associate Identifier with Long URL**: The short link service associates this unique identifier with the provided long URL and stores this information in a database or other persistent storage.
3. **Create the Short Link**: The generated unique identifier is combined with the short link service's domain name (e.g., http://nurl.ink) as a prefix to create the short link.
4. **Redirect**: When a user accesses the short link, the short link service receives the request, looks up the associated long URL based on the unique identifier, and redirects the user to the long URL.
5. **Tracking and Analytics**: Some short link services also provide tracking and analytics features, recording information such as the number of accesses, sources, geographic locations, and more.

   
Short links frequently appear in our daily lives. We often receive various marketing messages during promotional events and holidays, which include short links. These links help businesses monitor key information such as user behavior and click-through rates during marketing campaigns.


![IMG_8524](https://github.com/user-attachments/assets/03f09256-2088-4795-acff-91965b120e5b)


The main benefits of short links include, but are not limited to, the following aspects:

- **Enhanced User Experience**: Short links are easier for users to remember and share, improving the overall user experience.
- **Space Saving**: Short links are shorter than long URLs, saving character space, especially in character-limited contexts such as Twitter or SMS.
- **Aesthetics**: Short links are generally more visually appealing and concise, avoiding long strings of characters.
- **Tracking and Analytics**: Short links allow for tracking of access patterns, helping to understand user behavior and preferences.



## Features ✨

- A Bloom filter is used to determine whether a short URL already exists, providing far better performance than using a distributed lock combined with a database query approach
- To optimize the issue of querying the database under scenarios involving updates or expirations with a large number of requests, a caching mechanism for non-existent reads is implemented. Additionally, a distributed lock mutual exclusion strategy is used to reduce frequent database queries.
- Utilize the ”peak shaving” feature of RocketMQ message queues to handle the storage of monitoring information under scenarios with high volumes of short URL access.
- To ensure data consistency between the short URL cache and the database, a strategy of updating the database and then deleting the cache has been adopted. This guarantees data consistency between the two.
- To implement data modification functionality for short-link in high-traﬀic scenarios, I used Redisson distributed read-write locks to ensure data modification safety and consistency.
- Use Sentinel for QPS (queries per second) rate limiting on interface access to ensure the stable operation of the short-link system. When rate limiting rules are triggered, the system can perform degradation handling to ensure the availability of core functionalities.

## Demo Screenshots 📸
TODO



## Installation & Usage 🚀

### 1. Clone the repository

```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

### 2. Install dependencies


```bash
# Using npm
npm install

# Or using yarn
yarn install
```

### 3. Run the project
```
npm start
```

## Contributing 🤝
Contributions are welcome! Follow these steps:

- Fork this repository
- Create a new branch (git checkout -b feature/your-feature)
- Commit your changes (git commit -m 'Add some feature')
- Push to the branch (git push origin feature/your-feature)
- Open a Pull Request



## License 📄
This project is licensed under the MIT License.

