"""
Email рассылка о задачах в статусе 'Согласование'.
"""

import com.atlassian.mail.Email

static sendEmail(String emailAddr, String subject, String body) {
    def mailServer = ComponentAccessor.mailServerManager.defaultSMTPMailServer
    if (mailServer) {
        def email = new Email(emailAddr)
        email.setMimeType("text/html")
        email.setSubject(subject)
        email.setBody(body)
        mailServer.send(email)
    }
}

def today = (new Date()).format('dd.MM.YYYY')

def logins = ['user1', 'user2', 'user3']
def orglist = ['организация1', 'организация2', 'организация3']

def project = 'PROJ'
def projectname = 'Проект'

StringBuilder table = new StringBuilder()
table.append("<style>.text-border {border: 2px solid grey;padding: 10px;}</style>")
table.append("<font face='Arial'>Добрый день!<br>Информируем Вас о том, что на $today в рамках $projectname имеются следующие задачи ожидающие согласования:<br>")
table.append("<a href='https://jira.domen.com/projects/PROJ'>Ссылка на Проект</a><br>")

for (org in orglist) {
    table.append("<br><br><b><u>$org</u></b>")
    table.append('<br><i>Ожидают согласования - </i>' + Issues.search("project = PROJ AND Организация = '$org' AND status = 'Согласование'").size())
}

table.append('</font>')

def mailsappend = []
logins.each { // Сбор адресов по логинам.
    try {
        def mailrecep = Users.getByName("$it")
        if (mailrecep.emailAddress) {
            if (!mailsappend.contains(mailrecep.emailAddress)) {
                mailsappend.add(mailrecep.emailAddress)
            }
        }
    }
    catch (Exception e) {
        log.warn(e)
    }
}

if (mailsappend) {
    sendEmail(
        mailsappend.join(', '),
        "Проект: Напоминание о задачах, требующих согласования",
        table.toString()
    )
}