# 아키텍처
![Example Image](architecture.png)

- 먼저 correto 17 모듈 source,dependency
- 설정에 gralde correto17 맞추기

## 해야할것
```shell
cd infra/local
docker compose up
```

```shell
cd script
spotless-test
```

```shell
cd script
chmod +x pre-commit
```
- 확인하기: grale를 최신화하고 gradle tasks에 addGitPrecommitHook 실행
- cd .git/hooks에 pre-commit이라는 파일있는지 확인하기
