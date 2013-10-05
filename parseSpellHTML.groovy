import org.jsoup.Jsoup
import org.jsoup.nodes.Document

File spell = new File("C:\\Users\\user\\Documents\\hypertext_d20_srd\\www.d20srd.org\\srd\\spells\\acidArrow.htm");
Document doc = Jsoup.parse(spell, "utf-8")
def id = "1"

def name =  doc.body().select("h1").first().text()


def categories = doc.body().select("h4").select("a")
def school = categories.first().text()

def subschool = "NULL"
def descriptor = "NULL"

if (categories.size() > 1) {
    subschool = categories.get(1).text()
}

if (categories.size() > 2) {
    descriptor = categories.get(2).text()
}

def statBlock = doc.body().getElementsByClass("statBlock").select("tr")

def level = statBlock.get(0).select("td").get(0).text()
def components = statBlock.get(1).select("td").text()
def castingTime = statBlock.get(2).select("td").text()
def range = statBlock.get(3).select("td").text()
def effect = statBlock.get(4).select("td").text()
def duration = statBlock.get(5).select("td").text()
def savingThrow = statBlock.get(6).select("td").text()
def spellResistance = statBlock.get(7).select("td").text()
def description = doc.body().select("p").first().text()

def materialOrFocusList = doc.body().select("h6")
def materialComponent = "NULL"
def focus = "NULL"

if (materialOrFocusList.size() > 0) {
    if (materialOrFocusList.get(0).text().equals("Material Component")) {
        materialComponent = materialOrFocusList.get(0).nextElementSibling().text()
    }
    else if (materialOrFocusList.get(0).equals("Focus")) {
        focus = materialOrFocusList.get(0).nextElementSibling().text()
    }
}

if (materialOrFocusList.size() > 1) {
    if (materialOrFocusList.get(1).text().equals("Material Component")) {
        materialComponent = materialOrFocusList.get(1).nextElementSibling().text()
    }
    else if (materialOrFocusList.get(1).text().equals("Focus")) {
        focus = materialOrFocusList.get(1).nextElementSibling().text()
    }
}

println name
println school
println subschool
println descriptor
println level
println components
println castingTime
println range
println effect
println duration
println savingThrow
println spellResistance
println description
println materialComponent
println focus


def databaseString = /String acidArrowQueryString = "insert into " +
                SpellBookDatabaseManager.DB_NAME + 
                "." +
                SpellBookDatabaseManager.SPELL_TABLE_NAME + 
                "(" + 
                SpellBookDatabaseManager.SPELL_TABLE_ROW_ID + ", " + 
                SpellBookDatabaseManager.SPELL_TABLE_ROW_NAME + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_SCHOOL + ", " + 
                SpellBookDatabaseManager.SPELL_TABLE_ROW_SUBSCHOOL + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_DESCRIPTOR + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_LEVEL + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_COMPONENTS + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_CASTING_TIME + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_RANGE + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_EFFECT + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_DURATION + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_SAVING_THROW + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_SPELL_RESISTANCE + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_DESCRIPTION + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_MATERIAL_COMPONENT + ", " +
                SpellBookDatabaseManager.SPELL_TABLE_ROW_FOCUS + ", " +
                ")" +
                "${id},${name},${school},${subschool},${descriptor},${level},${components}," +
                "${castingTime},${range},${effect},${duration},${savingThrow}," +
                "${spellResistance},${description},${materialComponent},${focus}" +
                ");";/
println databaseString