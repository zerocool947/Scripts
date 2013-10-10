import org.jsoup.Jsoup
import org.jsoup.nodes.Document

//def id = 0

File spellsFile = new File("C:\\Users\\user\\Documents\\hypertext_d20_srd\\www.d20srd.org\\srd\\spells\\")
File outputFile = new File("C:\\Users\\user\\Documents\\parsedSpells.txt")
outputFile.setText("");

File[] spells = spellsFile.listFiles();
for (spell in spells) {
def name = "NULL"
def school = "NULL"
def subschool = "NULL"
def descriptor = "NULL"
def level = "NULL"
def components = "NULL"
def castingTime = "NULL"
def range = "NULL"
def target = "NULL"
def effect = "NULL"
def duration = "NULL"
def savingThrow = "NULL"
def spellResistance = "NULL"
//description can't be null anyways and needs to start blank
def description = ""
def materialComponent = "NULL"
def focus = "NULL"
def xpCost = "NULL"
//File spell = new File("C:\\Users\\user\\Documents\\hypertext_d20_srd\\www.d20srd.org\\srd\\spells\\acidArrow.htm")
//id = id+1
Document doc = Jsoup.parse(spell, "utf-8") 

name =  doc.body().select("h1").first().text()

if (name.equals("Greater (Spell Name)") || name.equals("Lesser (Spell Name)") || name.equals("Mass (Spell Name)")) {
    continue
}

def methodName = name.replaceAll(/[^A-Za-z]/,"")

name = name.replaceAll("'","�")

def categories = doc.body().select("h4").select("a")
school = categories.first().text()

if (categories.size() > 1) {
    subschool = categories.get(1).text()
}

if (categories.size() > 2) {
    descriptor = categories.get(2).text()
}

def statBlock = doc.body().getElementsByClass("statBlock")

level = statBlock.select("a[href\$=level]").first().parent().nextElementSibling().text()
if (!statBlock.select("[href\$=components]").isEmpty()) {
    components = statBlock.select("a[href\$=components]").first().parent().nextElementSibling().text()
}
if (!statBlock.select("a[href\$=castingTime]").isEmpty()) {
    castingTime = statBlock.select("a[href\$=castingTime]").first().parent().nextElementSibling().text()
}
if (!statBlock.select("a[href\$=range]").isEmpty()) {
    range = statBlock.select("a[href\$=range]").first().parent().nextElementSibling().text()
}
if (!statBlock.select("a[href\$=effect]").isEmpty()) {    
    effect = statBlock.select("a[href\$=effect]").first().parent().nextElementSibling().text()
}
if (!statBlock.select("a[href\$=targetorTargets]").isEmpty()) {
    target = statBlock.select("a[href\$=targetorTargets]").first().parent().nextElementSibling().text()
}
if (!statBlock.select("a[href\$=duration]").isEmpty()) {
    duration = statBlock.select("a[href\$=duration]").first().parent().nextElementSibling().text()
}
if (!statBlock.select("a[href\$=savingThrow]").isEmpty()) {
    savingThrow = statBlock.select("a[href\$=savingThrow]").first().parent().nextElementSibling().text()
}
if (!statBlock.select("a[href\$=spellResistance]").isEmpty()) {
    spellResistance = statBlock.select("a[href\$=spellResistance]").first().parent().nextElementSibling().text()
}

def paragraphElementStart =  doc.body().select("p")

for (paragraphElement in paragraphElementStart) {
    if (paragraphElement.parent() != null) {
        if (paragraphElement.parent().className().equals("footer")) {
            continue;
        }
    }

    if (paragraphElement.previousElementSibling() != null)  {
        if (paragraphElement.previousElementSibling().text().equals("Material Component")) {
            materialComponent = paragraphElement.text()
        }
        else if (paragraphElement.previousElementSibling().text().equals("Focus")) {
            focus = paragraphElement.text()
        }
        else if (paragraphElement.previousElementSibling().text().equals("XP Cost")) {
            xpCost = paragraphElement.text()
        }
        else {
            description += paragraphElement.text() + " "
        }
    }

}

description.replaceAll("\'","\'\'")

name = "\'"+name+"\'"
school = "\'"+school+"\'"
description = "\'"+description+"\'"
level = "\'"+level+"\'"

if(!subschool.equals("NULL")) {
    subschool = "\'"+subschool+"\'"
}
if (!descriptor.equals("NULL")) {
    descriptor = "\'"+descriptor+"\'"
}
if (!components.equals("NULL")) {
    components = "\'"+components+"\'"
}
if (!castingTime.equals("NULL")) {
    castingTime = "\'"+castingTime+"\'"
}
if (!target.equals("NULL")) {
    target = "\'"+target+"\'"
}
if (!range.equals("NULL")) {
    range = "\'"+range+"\'"
}
if (!effect.equals("NULL")) {
    effect = "\'"+effect+"\'"
}
if (!duration.equals("NULL")) {
    duration = "\'"+duration+"\'"
}
if (!savingThrow.equals("NULL")) {
    savingThrow = "\'"+savingThrow+"\'"
}
if (!spellResistance.equals("NULL")) {
    spellResistance = "\'"+spellResistance+"\'"
}
if (!focus.equals("NULL")) {
    focus = "\'"+focus+"\'"
}
if (!materialComponent.equals("NULL")) {
    materialComponent.replaceAll("\'", "\'\'")
    materialComponent = "\'"+materialComponent+"\'"
}
if (!xpCost.equals("NULL")) {
    xpCost = "\'"+xpCost+"\'"
}

//println level

def databaseString = /String ${methodName}QueryString = "insert into " +
                SpellBookDatabaseManager.SPELL_TABLE_NAME + 
                "(" + 
                SpellBookDatabaseManager.SPELL_TABLE_ROW_NAME + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_SCHOOL + ", " + 
                SpellBookDatabaseManager.SPELL_TABLE_ROW_SUBSCHOOL + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_DESCRIPTOR + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_LEVEL + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_COMPONENTS + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_CASTING_TIME + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_TARGET + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_RANGE + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_EFFECT + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_DURATION + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_SAVING_THROW + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_SPELL_RESISTANCE + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_DESCRIPTION + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_MATERIAL_COMPONENT + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_FOCUS + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_XP_COST +
                ")" +
                "VALUES(" + 
                "${name},${school},${subschool},${descriptor},${level},${components}," +
                "${castingTime},${target},${range},${effect},${duration},${savingThrow}," +
                "${spellResistance},${description},${materialComponent},${focus},${xpCost}" +
                ");";
                db.execSQL(${methodName}QueryString);/
outputFile << "${databaseString}\n\n"
}