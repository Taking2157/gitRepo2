## git 命令

设置全局用户名

```shell
git config --global user.name "Taking2157"
```

设置全局用户邮箱

```shell
git config --global user.email "1462212231@qq.com"
```

在桌面创建仓库并初始化仓库

```shell
cd ~/Desktop
mkdir gitRepository
cd gitRepository
git init
```

创建一个测试文件test.txt

```shell
touch test.txt
```

查看状态(git 不会管理该文件)

```shell
git status
On branch master

No commits yet

Untracked files:
  (use "git add <file>..." to include in what will be committed)
	test.txt

nothing added to commit but untracked files present (use "git add" to track)
```

将 test.txt 添加到暂存区，并查看状态

```shell
git add test.txt
git status
On branch master

No commits yet

Changes to be committed:
  (use "git rm --cached <file>..." to unstage)
	new file:   test.txt
```

将暂存区的 test.txt 添加到本地仓库中，并查看状态

```shell
git commit -m "添加了test.txt"
[master (root-commit) 73afe21] 添加了test.txt
 1 file changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 test.txt
git status
On branch master
nothing to commit, working tree clean
```

查看日志

```shell
git log
commit 73afe2186bfb31462062c3af5722a3d58de6e3d9 (HEAD -> master)
Author: Taking2157 <1462212231@qq.com>
Date:   Sun Nov 15 15:06:29 2020 +0800

    添加了test.txt
git log --pretty=oneline
73afe2186bfb31462062c3af5722a3d58de6e3d9 (HEAD -> master) 添加了test.txt
git log --oneline
73afe21 (HEAD -> master) 添加了test.txt
git reflog
73afe21 (HEAD -> master) HEAD@{0}: commit (initial): 添加了test.txt
```

回退版本(--hard 表示本地仓库指针移动的同时，重制暂存区，重制工作区，
				--mixed 表示本地仓库指针移动的同时，重制暂存区，不重制工作区，
				--soft 表示本地仓库指针移动，不重制暂存区，也不重制工作区，
				工作中常用的就是用 --hard)

```shell
git reflog
fddab66 (HEAD -> master) HEAD@{0}: commit: 修改test.txt,添加了Hello World内容
73afe21 HEAD@{1}: reset: moving to HEAD
73afe21 HEAD@{2}: commit (initial): 添加了test.txt
git reset --hard 73afe21
73afe21 (HEAD -> master) HEAD@{0}: reset: moving to 73afe21
fddab66 HEAD@{1}: commit: 修改test.txt,添加了Hello World内容
73afe21 (HEAD -> master) HEAD@{2}: reset: moving to HEAD
73afe21 (HEAD -> master) HEAD@{3}: commit (initial): 添加了test.txt
```

创建 test2.txt ，并添加内容 Hello World，将 test2.txt 添加到暂存区，查看状态

```shell
touch test2.txt
vim test2.txt
git add test2.txt
git status
On branch master
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
	new file:   test2.txt
```

将 test2.txt 添加到本地仓库

```shell
git commit -m "添加了test2.txt,内容为Hello\ World"
```

删除本地 test2.txt

```shell
rm text2.txt
```

将操作提交到暂存区，查看状态，再提交到本地仓库

```shell
git add test2.txt
git status
On branch master
Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
	deleted:    test2.txt
git commit -m "删除了text2.txt"
```

查看日志，回退到上个版本，查看当前目录下内容 (找回本地仓库删除的文件，暂存区类似)

```shell
git reflog
c04a884 (HEAD -> master) HEAD@{0}: reset: moving to c04a884
b38cb8a HEAD@{1}: reset: moving to b38cb8a
c04a884 (HEAD -> master) HEAD@{2}: commit: 删除了text2.txt
b38cb8a HEAD@{3}: commit: 添加了test2.txt,内容为Hello\ World
git reset --hard b38cb8a
ls
test.txt  test2.txt
```

比较工作区与暂存区的区别

```shell
git diff test.txt （单个文件比较）
diff --git a/test.txt b/test.txt
index 980a0d5..56571d1 100644
--- a/test.txt
+++ b/test.txt
@@ -1 +1,2 @@
 Hello World!
+This is test.txt.
git diff (所有文件比较)
diff --git a/test.txt b/test.txt
index 980a0d5..56571d1 100644
--- a/test.txt
+++ b/test.txt
@@ -1 +1,2 @@
 Hello World!
+This is test.txt.
diff --git a/test2.txt b/test2.txt
index 980a0d5..9637006 100644
--- a/test2.txt
+++ b/test2.txt
@@ -1 +1,2 @@
 Hello World!
+This is test2.txt.
```

比较暂存区与本地仓库区别

```shell
git diff HEAD
diff --git a/test.txt b/test.txt
index 56571d1..4c6fcb1 100644
--- a/test.txt
+++ b/test.txt
@@ -1,2 +1,3 @@
 Hello World!
 This is test.txt.
+Today is Sunday.
diff --git a/test2.txt b/test2.txt
index 9637006..bf7b907 100644
--- a/test2.txt
+++ b/test2.txt
@@ -1,2 +1,3 @@
 Hello World!
 This is test2.txt.
+Today is Sunday.
```

分支的查看，创建和切换

```shell
git branch -a （查看所有分支）
git branch -v
* master a9df493 删除了test3.txt
git branch dev
  dev    a9df493 删除了test3.txt
* master a9df493 删除了test3.txt
git checkout dev
Switched to branch 'dev'
* dev    a9df493 删除了test3.txt
  master a9df493 删除了test3.txt
```

在本地创建远程仓库的别名，并查看

```shell
git remote add orgin https://github.com/Taking2157/gitRepo2.git
git remote -v
origin	https://github.com/Taking2157/gitRepo2.git (fetch)
origin	https://github.com/Taking2157/gitRepo2.git (push)
```

push操作 (先更新本地库)

```shell
git push -f origin master
Enumerating objects: 5, done.
Counting objects: 100% (5/5), done.
Writing objects: 100% (3/3), 303 bytes | 303.00 KiB/s, done.
Total 3 (delta 0), reused 0 (delta 0)
To https://github.com/Taking2157/gitRepo2.git
   45bee89..a63a586  master -> master
```

pull操作 （相当于fetch & merge）

```shell
git pull origin master
From https://github.com/Taking2157/gitRepo2
 * branch            master     -> FETCH_HEAD
Already up to date.
```

SSH 免密码登陆

```shell
cd ~
ssh-keygen -t rsa -C "1462212231@qq.com"
Generating public/private rsa key pair.
Enter file in which to save the key (/Users/taojing/.ssh/id_rsa):
Created directory '/Users/taojing/.ssh'.
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
Your identification has been saved in /Users/taojing/.ssh/id_rsa.
Your public key has been saved in /Users/taojing/.ssh/id_rsa.pub.
The key fingerprint is:
SHA256:BvLJuMWnb5y30NGx7wi6kk/pbDpR3mXLbtAKyq1vSFo 1462212231@qq.com
The key's randomart image is:
+---[RSA 3072]----+
|                 |
|                 |
|    . .     .    |
|     * o.  .oo   |
|    . *oS..=o.   |
|     oE+ooo.+.   |
|    .=.B+oo+  .  |
|    . B=Boo.oo   |
|      o@Oo.o. .  |
+----[SHA256]-----+

cd .ssh
ls
id_rsa     id_rsa.pub
cat id_rsa.pub
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQCbIv6tZ8enHxAlkNaL1qbMqgrl/JPZhas/Sh0SgEuRFL1g6Jnmuc85PdsyUZTUproHwziTpNqOKr7Cmf7Q2g6d+HXQ1yO7kV4xdprwSMWNRQFja+CXhRryydw5d7M1xnNs1gWGZI/f22y8il4h7R15ZFEfexv8mDVweCVTghSEkCSXdcEb6yP40xq7TtPu3IRtdK9n9NqYvYV4nZV8gDMHepTiOn/68YtFBZbrFwDnV7u7tWZ3HfhTTF1gyaiVFK4wI2U7IBlXqncgnJ2WhugBFDh24t8RXVgSNzMGm6LJZJpfXYnphrI2v7m5dAADhSndqjWGTxDA2pLv8KFm7lgFeG45eXMQ0NdVciTA5Bs3MhcXC/vtxbts+N3+DMCKKm4GbYjmZgJ52T3QAiR8Q2KeTAFt0tmL5wBMwXNahWWBmLmc3iNCV04v+x1i6Anc9v91C2FJ91kKa5oezjlvYXsCT0Odw/SUf/O415noWx7rXAiVABXC8/ATVBnegtBNdUc= 1462212231@qq.com
进入github网站，然后进入settings，找到SSH and GPG keys，并添加新的ssh keys，Title 可以随便命名，然后把id_rsa.pub的内容粘贴到Key的输入框，再点击Add SSH Key，完成操作
```

从远程仓库拉取和推送

```shell
git pull https://github.com/Taking2157/gitRepo2.git master --allow-unrelated-histories
From https://github.com/Taking2157/gitRepo2
 * branch            master     -> FETCH_HEAD
Already up to date.
git push https://github.com/Taking2157/gitRepo2.git master
Everything up-to-date
```