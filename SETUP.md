# Как поднять окружение на другом компьютере

Всё ставится за 15–20 минут, нужен интернет и права администратора (Windows спросит через UAC).

## Что стоит на основном компе

| Что | Версия | Зачем |
|---|---|---|
| JDK Eclipse Temurin | 25.0.5 LTS | компилятор `javac`, JVM `java`, стандартная библиотека |
| IntelliJ IDEA | 2026.3.0.1 | IDE (для Java бесплатна, подписка не нужна) |
| Maven | 3.9.16 | сборка и тесты; отдельно не ставится — его качает `mvnw.cmd` |
| JUnit | 6.1.3 | тесты к задачам; качает Maven |
| Git | любой | уже был |

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

Должно быть `openjdk version "25.0.5..."` и `Temurin`. Если показывает другую версию — где-то в `PATH` стоит старая Java, найти её:

```powershell
where.exe java
```

## 2. IntelliJ IDEA

```powershell
winget install --id JetBrains.IntelliJIDEA --exact --source winget
```

Или скачать с [jetbrains.com/idea](https://www.jetbrains.com/idea/download/).

## 3. Забрать проект

Проект лежит на GitHub: [github.com/ktrand/java-learning](https://github.com/ktrand/java-learning).

```powershell
git clone https://github.com/ktrand/java-learning.git C:\projects\java-learning
```

Почта в коммитах — служебная GitHub, чтобы не светить личную. На новом компе один раз в папке проекта:

```powershell
git config user.email 93554733+ktrand@users.noreply.github.com
```

Прогресс между компами — через git: закончил день → `git add .`, `git commit -m "День N"`, `git push`; на другом компе перед занятием — `git pull`.

## 4. Открыть и проверить

1. IntelliJ → File → Open → папка проекта. Она откроется как Maven-проект (по `pom.xml`).
2. File → Project Structure → SDK: выбрать 25. Обычно IntelliJ находит его сама по `JAVA_HOME`.
3. Прогнать тесты из терминала в папке проекта:

```powershell
.\mvnw.cmd test "-Dtest=day01.*Test"
```

Первый запуск скачает Maven и JUnit в `%USERPROFILE%\.m2` — это минута-две. Пока задачи не решены, тесты красные с `TODO` — так и должно быть. Решённые дни — зелёные.

4. Проверить запуск одного файла:

```powershell
java -Dstdout.encoding=UTF-8 sandbox\day01\Hello.java
```

## Как собран проект (если делать с нуля)

Если копировать проект, этот раздел не нужен — всё уже лежит в папке.

- `pom.xml` — Java 25 (`maven.compiler.release`), JUnit 6.1.3 через `junit-bom`, плагины `maven-compiler-plugin` 3.16.0 (с `-Xlint:all`) и `maven-surefire-plugin` 3.6.1.
- `mvnw.cmd`, `mvnw`, `.mvn/wrapper/maven-wrapper.properties` — обёртка Maven. Сгенерирована один раз скачанным Maven 3.9.16:

```powershell
mvn -N org.apache.maven.plugins:maven-wrapper-plugin:3.3.4:wrapper "-Dmaven=3.9.16"
```

- `.mvn/jvm.config` — `-Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8`, чтобы кириллица в выводе `mvnw` не превращалась в `???`.
- `.gitignore` — `target/`, `out/`, `*.class`, личные файлы IntelliJ.

## Грабли Windows

- **Кириллица печатается как `???`** — если локаль Windows английская, Java пишет в консоль в Cp1252. Решение: `chcp 65001` в окне терминала или флаг `-Dstdout.encoding=UTF-8`. В IntelliJ и `mvnw` уже настроено.
- **Кириллица в аргументах командной строки** тоже ломается, причём лаунчер `java` раскрывает получившиеся `?` как маску файлов. Аргументы — латиницей.
- **PowerShell режет аргументы с точкой.** `-Dtest=day01.*Test` без кавычек сломается, нужно `"-Dtest=day01.*Test"`.
- **Старые окна терминала** не видят `JAVA_HOME` и `PATH` после установки — открыть новое (или перезапустить IntelliJ).
