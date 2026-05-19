'''
Уведомление в tg. В другой мессенджер делается аналогично.
Вызывается из Automation.
'''


import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import com.atlassian.sal.api.net.Request
import com.atlassian.sal.api.net.TrustedRequestFactory

def trustedRequestFactory = ComponentAccessor.getOSGiComponentInstanceOfType(TrustedRequestFactory)

def escapeMarkdownV2(String text) {
    def specialChars = ['_', '*', '[', ']', '(', ')', '~', '`', '>', '#', '+', '-', '=', '|', '{', '}', '.', '!']
    specialChars.each { ch ->
        text = text.replace(ch, '\\' + ch)
    }
    return text
}

def get_last_status() {
        def changeHistoryManager = ComponentAccessor.changeHistoryManager
        def old_status = ''
        changeHistoryManager.getChangeHistories(issue).reverseEach { history -> // в обратном порядке
        for (item in history.changeItems) {

            if(item.field == "status" && item.oldstring && !old_status ) {

                old_status = item.oldstring

            }
        }
    }
    return old_status
}

def api_key = '111111:FFFFFFF'
def chat_id = '-100100100100'

def text = """*Запрос\\: *[${escapeMarkdownV2(issue.key)}]($issue.url)
"""

if (get_last_status()) {
    text = text + '*Из статуса\\: *' + escapeMarkdownV2(get_last_status().toString()) + """
"""
}

if (issue.summary) {
    text = text + '*Тема\\: *' + escapeMarkdownV2(issue.summary) + """
"""
}

if (issue.getCustomFieldValue(12701)) {
    text = text + '*Система\\: *' + escapeMarkdownV2(issue.getCustomFieldValue(12000)[0].label) + """ 
"""
}

if (issue.priority) {
    text = text + '*Приоритет\\: *' + escapeMarkdownV2(issue.priority.name) + """
"""
}

if (issue.created) {
    text = text + '*Дата создания\\: *' + escapeMarkdownV2(issue.created.format('dd.MM.Y')) + """ 
""" // 'dd.MM.Y H:m'
}

if (issue.description) {
    if (issue.description.size() > 200) {
    text = text + '*Описание\\: *' + escapeMarkdownV2(issue.description[0..200]) + """ \\.\\.\\.
"""
    }
    else {
    text = text + '*Описание\\: *' + escapeMarkdownV2(issue.description.toString()) + """
"""
    }
}

def requestBody = [
    chat_id: chat_id,
    text: text,
    parse_mode: 'MarkdownV2'
]

def jsonBody = JsonOutput.toJson(requestBody)

def url1_full = "https://api.telegram.org/bot$api_key/sendMessage"

Request request = trustedRequestFactory.createRequest(Request.MethodType.POST, url1_full)
request.addHeader("Content-Type", "application/json")
request.setRequestBody(jsonBody)

def check = 0
def okValue = false

while(check !=8 && okValue == false) {
    check+=1
    try {
        def response = request.execute()

        def json = new JsonSlurper().parseText(response)
        okValue = json['ok']

        check = 8
    }
    catch(Exception e) {
        // addMessage(e.toString())
        }
}

