# VarietyStore
杂货铺（释放灵感）

# version1.1
远程仓库添加文件，本地添加文件，进行分支上传测试  
git init
git status
git pull origin master
git pull
git add  
git status
git commit -m "描述"
git push

# version1.2
#### 创建本地分支
git checkout -b xxx
#### 根据本地分支创建远程分支并绑定远程
git push -u origin xxx
#### 查看已绑定的所有分支
git branch -vv
#### 绑定远程分支
git branch --set-upstream-to=origin/xxx
#### 删除本地分支
git branch -d xxx
#### 删除远程分支
git push origin -d xxx
#### 若在浏览器上删除分支，本地还会有远程分支副本，删除：
 - git remote show origin [展示远程分支和本地分支的联系]
 - git remote prune origin [清楚本地远程分支副本]
