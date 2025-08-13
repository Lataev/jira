'''
Получение значений атрибутов пользователя AD из плагина Active Directory Attributes Sync
'''

import com.onresolve.scriptrunner.runner.customisers.WithPlugin
import com.onresolve.scriptrunner.runner.customisers.PluginModule
import com.intenso.jira.plugins.admanager.api.UserAttributeManager

@WithPlugin("com.intenso.jira.plugins.ad-integration-manager")
@PluginModule
UserAttributeManager userAttributeManager

def connectionName = "AD_server" // Название соединения с AD в натройках плагина.
def userName = 'user_name' // Имя пользователя.

def attributesMap = userAttributeManager.getAttributes(userName, connectionName) // Возвращает все атрибуты, которые настроены на синхронизацию в соединении.

// Соответствия названий атрибутов в Jira и атрибутов AD настраиваются в соединении. Значение атрибута можно получить по названию из настроек.
def department = attributesMap.Отдел
def title = attributesMap.Должность
def email = attributesMap."E-mail" // Атрибут с символами в названии пишется в кавычках.
def managerKey = attributesMap.Руководитель // Возвращает ключ пользователя Jira вида JIRAUSER111111
def manager = Users.getByKey(attributesMap.Руководитель) // Возвращает пользователя Jira по ключу.