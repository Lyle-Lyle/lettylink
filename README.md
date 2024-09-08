[![build status](https://github.com/opengoofy/hippo4j/actions/workflows/ci.yml/badge.svg?event=push)](https://github.com/opengoofy/hippo4j)
[![codecov](https://codecov.io/gh/opengoofy/hippo4j/branch/develop/graph/badge.svg?token=WBUVJN107I)](https://codecov.io/gh/opengoofy/hippo4j)
![maven](https://img.shields.io/maven-central/v/com.alibaba.otter/canal.svg)
[![license](https://img.shields.io/badge/license-Apache--2.0-green.svg)](http://www.apache.org/licenses/LICENSE-2.0)
![](https://img.shields.io/github/contributors/opengoofy/hippo4j)
[![percentage of issues still open](http://isitmaintained.com/badge/open/opengoofy/hippo4j.svg)](http://isitmaintained.com/project/opengoofy/hippo4j "percentage of issues still open")

![GitHub last commit (branch)](https://img.shields.io/github/last-commit/opengoofy/hippo4j/develop?color=orange)

## 简介

![](https://images-machen.oss-cn-beijing.aliyuncs.com/image-20231115133642504.png)

短链接（Short Link）是指将一个原始的长 URL（Uniform Resource Locator）通过特定的算法或服务转化为一个更短、易于记忆的
URL。短链接通常只包含几个字符，而原始的长 URL 可能会非常长。

短链接的原理非常简单，通过一个原始链接生成个相对短的链接，然后通过访问短链接跳转到原始链接。

如果更细节一些的话，那就是：

1. **生成唯一标识符**：当用户输入或提交一个长 URL 时，短链接服务会生成一个唯一的标识符或者短码。
2. **将标识符与长 URL 关联**：短链接服务将这个唯一标识符与用户提供的长 URL 关联起来，并将其保存在数据库或者其他持久化存储中。
3. **创建短链接**：将生成的唯一标识符加上短链接服务的域名（例如：http://nurl.ink ）作为前缀，构成一个短链接。
4. **重定向**：当用户访问该短链接时，短链接服务接收到请求后会根据唯一标识符查找关联的长 URL，然后将用户重定向到这个长 URL。
5. **跟踪统计**：一些短链接服务还会提供访问统计和分析功能，记录访问量、来源、地理位置等信息。

短链接经常出现在咱们日常生活中，大家总是能在某些活动节日里收到各种营销短信，里边就会出现短链接。帮助企业在营销活动中，识别用户行为、点击率等关键信息监控。

![](https://images-machen.oss-cn-beijing.aliyuncs.com/IMG_9858-20231126.jpg)

主要作用包括但不限于以下几个方面：

- **提升用户体验**：用户更容易记忆和分享短链接，增强了用户的体验。
- **节省空间**：短链接相对于长 URL 更短，可以节省字符空间，特别是在一些限制字符数的场合，如微博、短信等。
- **美化**：短链接通常更美观、简洁，不会包含一大串字符。
- **统计和分析**：可以追踪短链接的访问情况，了解用户的行为和喜好。



# Letty survey

![License](https://img.shields.io/github/license/Lyle-Lyle/letty-survey)
![Stars](https://img.shields.io/github/stars/Lyle-Lyle/letty-survey)
![Issues](https://img.shields.io/github/issues/Lyle-Lyle/letty-survey)

## Project Overview 📖
This project is also a demonstration of the work I did during my internship. 他是公司营销平台下的短链接系统，服务于所有接入的用户，目的主要是为了增强用户体验。短链接（Short Link）是指将一个原始的长 URL（Uniform Resource Locator）通过特定的算法或服务转化为一个更短、易于记忆的
URL。短链接通常只包含几个字符，而原始的长 URL 可能会非常长。

短链接的原理非常简单，通过一个原始链接生成个相对短的链接，然后通过访问短链接跳转到原始链接。
![](https://images-machen.oss-cn-beijing.aliyuncs.com/image-20231115133642504.png)

如果更细节一些的话，那就是：

1. **生成唯一标识符**：当用户输入或提交一个长 URL 时，短链接服务会生成一个唯一的标识符或者短码。
2. **将标识符与长 URL 关联**：短链接服务将这个唯一标识符与用户提供的长 URL 关联起来，并将其保存在数据库或者其他持久化存储中。
3. **创建短链接**：将生成的唯一标识符加上短链接服务的域名（例如：http://nurl.ink ）作为前缀，构成一个短链接。
4. **重定向**：当用户访问该短链接时，短链接服务接收到请求后会根据唯一标识符查找关联的长 URL，然后将用户重定向到这个长 URL。
5. **跟踪统计**：一些短链接服务还会提供访问统计和分析功能，记录访问量、来源、地理位置等信息。

短链接经常出现在咱们日常生活中，大家总是能在某些活动节日里收到各种营销短信，里边就会出现短链接。帮助企业在营销活动中，识别用户行为、点击率等关键信息监控。
![](https://images-machen.oss-cn-beijing.aliyuncs.com/IMG_9858-20231126.jpg)
主要作用包括但不限于以下几个方面：

- **提升用户体验**：用户更容易记忆和分享短链接，增强了用户的体验。
- **节省空间**：短链接相对于长 URL 更短，可以节省字符空间，特别是在一些限制字符数的场合，如微博、短信等。
- **美化**：短链接通常更美观、简洁，不会包含一大串字符。
- **统计和分析**：可以追踪短链接的访问情况，了解用户的行为和喜好。

## Features ✨

- surveys management(Creat, edit, Update, delete), surveys list page, starred survey page, trash bin page
- low-code editor, drag-and-drop components
- statistics based on responses
- basic user login and sign up feature

## Demo Screenshots 📸
All the data I used in the demostration is from simulation data generator Mock.js(https://github.com/nuysoft/Mock). I also included that program in this project, feel free to check.

Home page
<img width="1920" alt="image" src="https://github.com/user-attachments/assets/6ea703bf-6873-47e9-a2ad-8121e375883d">

list of surveys
<img width="1920" alt="image" src="https://github.com/user-attachments/assets/31722767-82e5-4755-adb6-f1cdf5678b19">

create a survey
<img width="1920" alt="image" src="https://github.com/user-attachments/assets/aed3f970-8544-427f-a0c3-d0bbb9507508">
<img width="1920" alt="image" src="https://github.com/user-attachments/assets/71987d73-8857-4e6b-a7af-b4aeb63488d2">

edit a survey
<img width="1911" alt="image" src="https://github.com/user-attachments/assets/6fd7eec0-0c16-4bff-912d-77f0f5752b4a">

statistics
<img width="1898" alt="image" src="https://github.com/user-attachments/assets/85cf6a13-2461-4eae-a301-8c05eab8a71a">


starred surveys
<img width="1920" alt="image" src="https://github.com/user-attachments/assets/cae24d7a-475b-42c3-89d4-255a2e101bbf">



Trash bin
<img width="1920" alt="image" src="https://github.com/user-attachments/assets/cad1beba-3cb1-4281-ace9-0ff22c578c8e">





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

