'''
Запуск локального скрипта с передачей переменных
'''

def issue = Issues.getByKey('PROJ-001')

def vars = [
    "issue": issue
]

def scriptFile = new File("/var/atlassian/application-data/jira/scripts/Scripts/script_name.groovy")

if (scriptFile.exists()) {
    def shell = new GroovyShell(new Binding(vars))
    shell.evaluate(scriptFile)
}

// В удалённом скрипте issue будет работать как переменная