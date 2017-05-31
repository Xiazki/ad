# ad
#javaweb/java服务自动化部署 功能持续开发中
项目部署与协作平台主要需求包括项目部署，项目定时部署，项目管理，问题发布，问题处理，问题是否解决验证，开发任务管理，个人待办管理，消息通知，团队即时通讯，问题广场，用户管理，权限管理，角色管理。系统用户主要分为三种，管理员，开发工程师，测试工程师，其中开发工程师和测试工程师的权限基本项目只是负责的中重点不同，管理员可以创建用户账号，并为其配置角色和权限，可以创建删除项目，开发工程师和测试工程师能向管理员申请一个参与项目，参与项目后能查看项目详情，项目参与成员，能创建开发任务，创建个人待办任务，测试工程师重点负责发起一个问题处理流程，将问题发布给开发工程师，开发工程师处理完后由测试工程师进行验证，验证通过后才能将问题置为解决，用户还能将遇到的问题发布到问题广场上，发布之后所有人都能看见并能评论给出自己的答案。系统还应能查看项目的日志，如果项目发生错误并能进行提醒。系统还需要实现一个简单的即时通讯，以便更好的交流。
基于上述需求，本系统应该具有下面几个功能：
+ 项目部署；
+ 项目日志查看，异常错误消息通知；
+ 项目管理，包括项目创建，修改和删除；
+ 权限管理，包括用户管理，权限管理，角色管理；
+ 用户登陆，登出；
+ 问题处理流程；
+ 开发任务管理，包括开发任务创建，查看；
+ 个人待办任务管理包括创建个人待办，完成待办；
+ 团队即时通讯，团队成员可以在一起进行聊天；
+ 问题广场，用户可以发布自己的问题到问题广场，用户可以才问题下评论给出自己的答案。
