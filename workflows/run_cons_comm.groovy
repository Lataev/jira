"""
Запуск консольной команды на сервере Jira (Ubuntu).
Здесь запускается python скрипт, который принимает аргумент в виде ключа задачи.
"""

def processBuilder = new ProcessBuilder(
    "/usr/bin/python3.10",
    "/home/user/scripts/python_script.py",
    issue.key
)

processBuilder.redirectErrorStream(true)
def process = processBuilder.start()

def output = process.inputStream.text
log.warn(output)


// Получение значений аргументов в Python
// import sys
// issue_key = sys.argv[1]