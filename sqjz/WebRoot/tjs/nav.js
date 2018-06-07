// 导航栏配置文件
var outlookbar=new outlook();
var t;
/*
t=outlookbar.addtitle('基本设置','系统设置',1)
outlookbar.additem('查看个人资料',t,'profile.html')
outlookbar.additem('修改个人资料',t,'reset_info.php')
outlookbar.additem('更改登录密码',t,'chanagepass.html')

t=outlookbar.addtitle('广告设置','系统设置',1)
outlookbar.additem('登录文学论坛',t,'../vbb/forumdisplay.php?s=320e689ffabc5daa0be8b02c284d9968&forumid=39')
outlookbar.additem('发出电子邮件',t,'mailto:pobear@newmail.dlmu.edu.cn')

t=outlookbar.addtitle('新闻设置','系统设置',1)
outlookbar.additem('尚未通过文章',t,'un_pass.php')
outlookbar.additem('已经通过文章',t,'al_pass.php')
outlookbar.additem('修改现有文章',t,'modify.php')
outlookbar.additem('撰写最新文章',t,'sub_new.php')
outlookbar.additem('投稿给文学报',t,'#')

*/

t=outlookbar.addtitle('用户管理','后台管理',1)
outlookbar.additem('用户列表',t,'/sys/user/list.action')
outlookbar.additem('添加用户',t,'/sys/user/initAdd.action')
//outlookbar.additem('添加用户',t,'/admin/user/add.jsp')

t=outlookbar.addtitle('角色管理','后台管理',1)
outlookbar.additem('角色列表',t,'/sys/role/list.action')
outlookbar.additem('添加角色',t,'/admin/role/add.jsp')

t=outlookbar.addtitle('权限管理','后台管理',1)
outlookbar.additem('权限列表',t,'/admin/action/index.jsp')
//outlookbar.additem('增加权限',t,'/admin/action/add.jsp')


t=outlookbar.addtitle('系统日志','后台管理',1)
outlookbar.additem('操作日志',t,'/sys/xtrz/list.action?rzlx=1')
/*
t=outlookbar.addtitle('退出系统','管理首页',1)
outlookbar.additem('点击退出登录',t,'loginout.php')
*/