'''
Создание запроса в Jira с разными типами полей
'''


from datetime import datetime
from jira import JIRA
from secret import auth_data


jira = JIRA(
    token_auth=auth_data.jira_log_pass().token,
    options={"server": auth_data.jira_log_pass().server, "verify": False},
)

today = datetime.now()
today_jira_format = str(today.strftime("%Y-%m-%dT%H:%M:%S.0+0300"))

data = {
    "project": {"key": "proj"},  # Ключ проекта в Jira
    "issuetype": {"name": "Задача"},  # Тип задачи
    "summary": "Заголовок",
    "description": "Описание",
    "customfield_10300": "Текст",  # Текстовое поле
    "customfield_10301": [{"key": "KEY-11111"}],  # Поле Актива, заполнение по ключу объекта
    "customfield_10302": {"value": "Option1"},  # Поле выбора
    "customfield_10303": today_jira_format,  # Поле даты
}

# Создание задачи
new_issue = jira.create_issue(fields=data)
print(f"Создана задача с ключом: {new_issue.key}")

# Обновление данных в новой задаче
new_issue.update(fields={
    "customfield_10300": "Текст2", 
    "customfield_10302": {"value": "Option2"}
    }
)
new_issue.update(customfield_10301=[{"set": [{"key": "DICT-22222"}]}])
