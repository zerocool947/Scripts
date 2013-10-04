import org.jsoup.Jsoup
import org.jsoup.nodes.Document

File spell = new File("C:\\Users\\user\\Documents\\hypertext_d20_srd\\www.d20srd.org\\srd\\spells\\animatePlants.htm");
Document doc = Jsoup.parse(spell, "utf-8")
def id = "1"
def name =  doc.body().select("h1").first().text()
def school = doc.body().select("h4").first().select("a").first().text()
def subschool = doc.body().select("h4").first().select("a").get(1).text()
def descriptor = doc.body().select("h4").first().select("a").get(2).text()
def level = doc.body().getElementsByClass("statBlock").select("tr").get(0).select("td").get(0).text();

println name
println school
//println subschool
//println descriptor
println level

/*def databaseString = /String acidArrowQueryString = "insert into " +
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
                "${id},${name},${school},${subschool},${descriptor${level},${components}," +
                "${castingTime},${range},${effect},${duration},${savingThrow}," +
                "${spellResistance},${description},${materialComponent},${focus}" +
                ");";/
println databaseString*/