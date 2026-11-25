# Как поднять окружение и заниматься вдвоём

Всё ставится за 15–20 минут, нужен интернет и права администратора (Windows спросит через UAC).

## Что нужно

| Что | Версия | Зачем |
|---|---|---|
| JDK Eclipse Temurin | 25 LTS | компилятор `javac`, JVM `java`, стандартная библиотека |
| IntelliJ IDEA | 2026.3 | IDE (для Java бесплатна, подписка не нужна) |
| Git | любой | забрать курс и хранить свои решения |
| аккаунт GitHub | — | пушить в свою ветку |
| Maven | 3.9.16 | сборка и тесты; отдельно не ставится — его качает `mvnw.cmd` |
| JUnit | 6.1.3 | тесты к задачам; качает Maven |

## 1. JDK 25

```powershell
winget install --id EclipseAdoptium.Temurin.25.JDK --exact --source winget --custom "ADDLOCAL=FeatureMain,FeatureEnvironment,FeatureJarFileRunWith,FeatureJavaHome"
```

`--custom` нужен, чтобы установщик сам прописал `JAVA_HOME` (без него пропишется только `PATH`).

Без winget: скачать MSI с [adoptium.net](https://adoptium.net) (Temurin 25, Windows x64, JDK) и в установщике включить пункт «Set JAVA_HOME variable» — по умолчанию он выключен.

Проверка — **в новом окне** терминала (старые окна про новую Java не знают):

```powershell
java -version
```

Должно быть `openjdk version "25..."` и `Temurin`. Если показывает другую версию — где-то в `PATH` стоит старая Java, найти её:

```powershell
where.exe java
```

## 2. IntelliJ IDEA и Git

```powershell
winget install --id JetBrains.IntelliJIDEA --exact --source winget
```

Или скачать с [jetbrains.com/idea](https://www.jetbrains.com/idea/download/). Git, если его нет:

```powershell
winget install --id Git.Git --exact --source winget
```

## 3. Доступ к репо

Курс лежит на GitHub: [github.com/ktrand/java-learning](https://github.com/ktrand/java-learning). Читать его может кто угодно, а пушить — только участники.

Второго участника добавляет ktrand: на странице репо Settings → Collaborators → Add people → логин на GitHub. Приглашение придёт на почту и в уведомления GitHub — его нужно принять.

## 4. Забрать проект и завести свою ветку (один раз на каждом компе)

```powershell
git clone https://github.com/ktrand/java-learning.git C:\projects\java-learning
```

```powershell
cd C:\projects\java-learning
```

**Почта для коммитов.** Репо публичный, и почта из коммитов видна всем. Поэтому ставим служебную почту GitHub: GitHub → Settings → Emails → включить «Keep my email addresses private», там будет адрес вида `12345678+логин@users.noreply.github.com`. В папке проекта:

```powershell
git config user.name "твой-логин"
```

```powershell
git config user.email "12345678+логин@users.noreply.github.com"
```

У ktrand адрес `93554733+ktrand@users.noreply.github.com`.

**Своя ветка** — по логину GitHub. Если её ещё нет (первый раз вообще):

```powershell
git switch -c твой-логин
```

```powershell
git push -u origin твой-логин
```

Если ветка уже есть (второй комп того же человека, например `ktrand`) — просто переключиться, git сам свяжет её с GitHub:

```powershell
git switch ktrand
```

Заполни шапку `progress.md` (кто и какая ветка) — это твой журнал.

## 5. Открыть и проверить

1. IntelliJ → File → Open → папка проекта. Она откроется как Maven-проект (по `pom.xml`).
2. File → Project Structure → SDK: выбрать 25. Обычно IntelliJ находит его сама по `JAVA_HOME`.
3. Прогнать тесты из терминала в папке проекта:

```powershell
.\mvnw.cmd test "-Dtest=day01.*Test"
```

Первый запуск скачает Maven и JUnit в `%USERPROFILE%\.m2` — это минута-две. Пока задачи не решены, тесты красные с `TODO` — так и должно быть.

4. Проверить запуск одного файла:

```powershell
java -Dstdout.encoding=UTF-8 sandbox\day01\Hello.java
```

## 6. Каждый день

Ветки:

- `main` — общий курс: уроки, заготовки, тесты. Новые дни туда кладёт Claude. **Свои решения в `main` не коммитим.**
- твоя ветка — решения, `progress.md`, эксперименты в `sandbox/`.

Перед занятием — забрать свежий урок в свою ветку:

```powershell
git switch твой-логин
```

```powershell
git pull
```

```powershell
git merge origin/main
```

`git pull` скачивает с GitHub всё, в том числе свежий `main`, а `merge` вливает новые уроки в твою ветку. Конфликтов быть не должно: в `main` только добавляются новые дни, а уже выданные заготовки не меняются.

Решил задачи, записал итог в `progress.md` — сохранить и отправить:

```powershell
git add .
```

```powershell
git commit -m "День 1"
```

```powershell
git push
```

В IntelliJ то же самое есть в меню Git: Update Project, Merge (ветка `origin/main`), Commit, Push.

**Посмотреть решения друг друга** (лучше после того, как оба решили день):

- решения одного человека на GitHub: `https://github.com/ktrand/java-learning/compare/main...логин`;
- два решения рядом, локально:

```powershell
git fetch
```

```powershell
git diff origin/ktrand origin/логин -- src/main/java/day01
```

Если случайно закоммитил решение в `main` — не пушь, напиши Claude, он поможет перенести коммит в свою ветку.

## Как собран проект (если делать с нуля)

Если проект клонирован, этот раздел не нужен — всё уже лежит в репо.

- `pom.xml` — Java 25 (`maven.compiler.release`), JUnit 6.1.3 через `junit-bom`, плагины `maven-compiler-plugin` 3.16.0 (с `-Xlint:all`) и `maven-surefire-plugin` 3.6.1.
- `mvnw.cmd`, `mvnw`, `.mvn/wrapper/maven-wrapper.properties` — обёртка Maven. Сгенерирована один раз скачанным Maven 3.9.16:

```powershell
mvn -N org.apache.maven.plugins:maven-wrapper-plugin:3.3.4:wrapper "-Dmaven=3.9.16"
```

- `.mvn/jvm.config` — `-Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8`, чтобы кириллица в выводе `mvnw` не превращалась в `???`.
- `.gitignore` — `target/`, `out/`, `*.class`, файлы IntelliJ (`.idea/`, `*.iml`); `.gitattributes` — концы строк (`mvnw` с LF, `.cmd` с CRLF).

## Грабли Windows

- **Кириллица печатается как `???`** — если локаль Windows английская, Java пишет в консоль в Cp1252. Решение: `chcp 65001` в окне терминала или флаг `-Dstdout.encoding=UTF-8`. В IntelliJ и `mvnw` уже настроено.
- **Кириллица в аргументах командной строки** тоже ломается, причём лаунчер `java` раскрывает получившиеся `?` как маску файлов. Аргументы — латиницей.
- **PowerShell режет аргументы с точкой.** `-Dtest=day01.*Test` без кавычек сломается, нужно `"-Dtest=day01.*Test"`.
- **Старые окна терминала** не видят `JAVA_HOME` и `PATH` после установки — открыть новое (или перезапустить IntelliJ).
