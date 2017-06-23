package com.example.tehlivets.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS recipes(id INTEGER PRIMARY KEY UNIQUE NOT NULL, name TEXT NOT NULL, image BLOB, short_description TEXT, mark BOOLEAN NOT NULL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS products(rec_id INTEGER NOT NULL, name TEXT NOT NULL, product_count INTEGER, foreign key (rec_id) references recipes(id));");
        db.execSQL("CREATE TABLE IF NOT EXISTS steps(rec_id INTEGER NOT NULL, number_of_step INTEGER NOT NULL, image BLOB, description TEXT,  foreign key (rec_id) references recipes(id));");
        db.execSQL("CREATE TABLE IF NOT EXISTS tags(rec_id INTEGER NOT NULL, name TEXT NOT NULL, foreign key(rec_id) references recipes(id));");

//        db.execSQL("INSERT INTO recipes(id,name,short_description,mark,mark) values(1,'Gofry ziemniaczane obłędnie pyszne','Ten przepis to jedno z moich ulubionych dań. Starte ziemniaki po opieczeniu w gofrownicy są pyszne i chrupiące, tworząc coś na kształt gofro-placka (ale przyjmijmy, że będę na nie mówić gofry ziemniaczane). A limonkowy sos z awokado ma nie tylko świetny smak, ale też lekko majonezową konsystencję sprawiającą, że gofry nie będą za suche i można je jeść bez końca. Jeżeli ktoś twierdzi, że wegańskie przekąski są mało porywające, koniecznie zeserwuj mu takie gofry - gwarantuję, że będzie zachwycony! Jakie to dobre!" +
//                "Pomysł na to danie zaczerpnąłem (ta, ja, Michał, taka niespodzianka!) z amerykańskiego show Munchies. To, że go odkryłem, to zresztą część mojej większej przygody z gofrownicą i zgłębiania jej roli w amerykańskim codziennym gotowaniu (pieczeniu?). Amerykanie ładują do swoich gofrownic dosłownie wszystko i jak widać na przykładzie tego przepisu, często jest to coś bardziej fit niż fast foodowego. Najlepsze w tych eksperymentach jest to, że nie wymagają wielkich przygotowań - wystarczy wyjąć gofrownicę (każdy ją ma, a kto nie ma to polecam taką), sięgnąć po kilka podstawowych składników i poczekać kilka minut na efekty. Liczy się pomysł, ale ma być prosto." +
//                "No ale koniec przynudzania. Zapraszam na gofry ziemniaczane. Koniecznie zrób je razem z sosem, bo ten sos to połowa sukcesu - jest tak dobry i łatwy do zrobienia, że warto o nim pamiętać też przy innych okazjach. Jeżeli nie masz do niego limonki, wybierz cytrynę. Awokado dostaniesz teraz praktycznie wszędzie, tylko pamiętaj, aby było już dojrzałe (czyli miękkie) - jeżeli jest jeszcze twarde (a takie bywają czasem prosto ze sklepu), odłóż je na kilka dni. Szkoda byłoby się zniechęcić do tak pysznego dania z powodu niedobrego awokado :)', 0)");
//
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'2 pasternaki lub pietruszki',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'2-3 ziemniaki',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'szczypiorek',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'1 łyżka oliwy z oliwek (lub oleju)',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'1 łyżeczka świeżego tymianku',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'1 awokado',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'3 łyżki soku z limonki',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'3 łyżki oliwy',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'2 łyżki kolendry świeżej (ew. odrobinę suszonej)',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'sól, pieprz',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'odrobina wody, by uzyskać odpowiednią konsystencję (wedle uznania)',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'2-3 pomidory',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'sól, pieprz do smaku',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'2 łyżki oliwy z oliwek',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'1 łyżeczka świeżego tymianku (ew. suszony)',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'3 ząbki czosnku',1)");
//        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'do podania świeże kiełki lub szczypior',1)");
//
//        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#Dania wegetariańskie')");
//        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#gofry')");
//        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#przepisy wegańskie')");
//        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#awokado')");
//        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#ziemniaki')");
//        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#pietruszka')");
//
//        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(1,1,'Zaczynam od przyszykowania pomidorów, bo potem nie będzie czasu. Sparzam je, aby zdjąć skórkę i kroję na kawałki. Następnie posypuję przyprawami (pieprz, sól, tymianek), obkładam drobno posiekanym czosnkiem i polewam oliwą. Wykładam na papier do pieczenia i piekę w 180 stopniach C przez ok. 10-15 minut (tak, aby nie spalić)')");
//        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(1,2,'Ziemniaki i pietruszkę ścieram na średnich oczkach. Dodaję odrobinę oliwy, posiekany szczypiorek i przyprawy: sól, pieprz, tymianek.')");
//        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(1,3,'Wykładam porcje na rozgrzaną gofrownicę polaną olejem. Czas pieczenia zależy od mocy gofrownicy, rozmiaru porcji i rodzaju ziemniaków. Chodzi o to, aby gotowy gofr nie rozwarstwiał się - u mnie to trwało ok. 6-8 minut.')");
//        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(1,4,'Szykuję sos z awokado. Obieram awokado (najwygodniej łyżką).')");
//        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(1,5,'Blenduję lub miksuję awokado z przyprawami (kolendra, sól, pieprz) oraz oliwą. Dodaję odrobinę wody, aby łatwiej się blendowało i aby sos nie był za gęsty. Gofry podaję z sosem i kiełkami. Smacznego!')");

        //Przepisy
        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(1,'Gofry ziemniaczane obłędnie pyszne','Ten przepis to jedno z moich ulubionych dań. Starte ziemniaki po opieczeniu w gofrownicy są pyszne i chrupiące, tworząc coś na kształt gofro-placka (ale przyjmijmy, że będę na nie mówić gofry ziemniaczane). A limonkowy sos z awokado ma nie tylko świetny smak, ale też lekko majonezową konsystencję sprawiającą, że gofry nie będą za suche i można je jeść bez końca.',0)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'2 pasternaki lub pietruszki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'2-3 ziemniaki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'szczypiorek',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'1 łyżka oliwy z oliwek (lub oleju)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'1 łyżeczka świeżego tymianku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'1 awokado',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'3 łyżki soku z limonki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'3 łyżki oliwy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'2 łyżki kolendry świeżej (ew. odrobinę suszonej)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'sól, pieprz',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'odrobina wody, by uzyskać odpowiednią konsystencję (wedle uznania)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'2-3 pomidory',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'sól, pieprz do smaku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'2 łyżki oliwy z oliwek',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'1 łyżeczka świeżego tymianku (ew. suszony)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'3 ząbki czosnku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(1,'do podania świeże kiełki lub szczypior',1)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#dania wegetariańskie')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#gofry')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#przepisy wegańskie')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#awokado')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#ziemniaki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(1,'#pietruszka')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(1,1,'Zaczynam od przyszykowania pomidorów, bo potem nie będzie czasu. Sparzam je, aby zdjąć skórkę i kroję na kawałki. Następnie posypuję przyprawami (pieprz, sól, tymianek), obkładam drobno posiekanym czosnkiem i polewam oliwą. Wykładam na papier do pieczenia i piekę w 180 stopniach C przez ok. 10-15 minut (tak, aby nie spalić)')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(1,2,'Ziemniaki i pietruszkę ścieram na średnich oczkach. Dodaję odrobinę oliwy, posiekany szczypiorek i przyprawy: sól, pieprz, tymianek.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(1,3,'Wykładam porcje na rozgrzaną gofrownicę polaną olejem. Czas pieczenia zależy od mocy gofrownicy, rozmiaru porcji i rodzaju ziemniaków. Chodzi o to, aby gotowy gofr nie rozwarstwiał się - u mnie to trwało ok. 6-8 minut.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(1,4,'Szykuję sos z awokado. Obieram awokado (najwygodniej łyżką).')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(1,5,'Blenduję lub miksuję awokado z przyprawami (kolendra, sól, pieprz) oraz oliwą. Dodaję odrobinę wody, aby łatwiej się blendowało i aby sos nie był za gęsty. Gofry podaję z sosem i kiełkami. Smacznego!')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(2,'Burgery brokułowe','Smakują mi burgery warzywne, to świetne urozmaicenie codziennego menu. Oto przepis na burgery brokułowe, delikatne, zielone, smaczne. Możesz je zajadać same (dobre też na zimno) lub z bułką i dodatkami. Burgery brokułowe robię z dodatkiem kaszy, są bardzo sycące.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(2,'#dania wegetariańskie')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(2,'#przepisy wegańskie')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(2,'#wegańskie burgery')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'100 g kaszy jaglanej + 300 ml wody',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'ok. 250 g brokuła (mniej więcej połowa)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'1 ząbek czosnku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'1/2 cebuli',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'2 łyżki zmielonego siemienia lnianego + 60 ml wrzątku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'ok. 2 łyżki mąki jaglanej lub kukurydzianej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'ok. 2 łyżki mąki jaglanej lub kukurydzianej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'3 łyżki uprażonego słonecznika',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'1 łyżka oliwy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'1/2 łyżeczki papryki słodkiej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'1/3 łyżeczki ostrej papryki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(2,'pieprz, sól',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(2,1,'Brokuła zalewam wrzątkiem, a po chwili lodowatą wodą, czyli hartuję. A potem siekam w malakserze lub w szatkownicy (ew. maszynce do mięsa z dużymi oczkami), możesz też posiekać ręcznie.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(2,2,'Kaszę zalewam ilością wody z przepisu, dodaję szczyptę soli i gotuję aż wchłonie wodę. Przestudzoną rozdrabniam i mieszam z zalanym siemieniem lnianym (będzie mieć taką żelową konsystencję to siemie)')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(2,3,'Mieszam z brokułem. Dodaję podsmażoną na oliwie, drobno posiekaną cebulkę i słonecznik.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(2,4,'Dodaję przyprawy')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(2,5,'Formuję kotlety, układam je na posmarowanym oliwą papierze do pieczenia. Piekę ok. 30 minut w 180C, ja na ostatnie 10 minut obracam na drugą stronę.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(3,'Sumeshi. Ryż do sushi','Zaczynamy przygodę z sushi. Jednak jedną z najważniejszych czynności podczas przygotowania sushi jest ryż, a w zasadzie gotowanie ryżu a potem jego zaprawienie. Najwygodniej przygotować sumeshi, czyli ryż do sushi korzystając z garnka do ryżu, ale można też go ugotować w zwykłym garnku. O co tak naprawdę chodzi? Ryż musi być dobrze zaparowany. Odpowiednio kleisty.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(3,'#sushi')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(3,'#nauka gotowania')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(3,'1 szklanka ryżu do sushi',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(3,'ok. 250-300 ml wody (trochę więcej niż szklanka)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(3,'2 łyżki octu ryżowego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(3,'1,5 łyżeczki cukru lub cukru pudru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(3,'1/2 łyżeczki soli',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(3,1,'Najpierw ryż (bez względu na sposób gotowania!) należy dokładnie przepłukać przynajmniej trzy razy. Ostrożnie mieszamy i wypłukujemy.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(3,2,'Zlewamy taka mleczną wodę i powtarzamy. Trzy razy wystarczy. Odstawiamy na kilkanaście minut na sitku.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(3,3,'Przygotowuję też zaprawę do ryżu, mieszam ocet z cukrem i solą.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(3,4,'GOTOWANIE RYŻU NA SUSHI W GARNKU DO RYŻU: w sumie tu nie ma wielkiej filozofii, wrzucam przepłukany ryż i wlewam wodę i zostawiamy do gotowania a potem jeszcze do odtajania (zaparowania). Po przestudzeniu zalewam dressingiem i mieszam delikatnie, ale dokładnie.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(3,5,'GOTOWANIE RYŻU NA SUSHI NORMALNIE: ryż wsypuję, zalewam wodą i pod przykryciem doprowadzam do wrzenia (ok. 5 minut). Mieszam ryż od spodu na minimalnym ogniu a potem gotuję 10 minut na minimalnym ogniu aż wchłonie wodę. Mieszam aby to co na dole było na górze i jeszcze podgrzewam ok. 1 minuty. Zdejmuję z ognia i odstawiam pod przykryciem na 10 minut. Po przestudzeniu mieszam z dressingiem delikatnie, ale bardzo dokładnie. SPOSÓB 2: gotuje ryż ok. 10 minut i zostawiam na 25 minut by zaparował. A potem mieszam z zalewą jw.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(4,'Pomysły na sushi','Zapraszam Cię na garść pomysłów na sushi. Jakie sushi zrobić w domu, jakie składniki wybrać i co z czym łączyć.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(4,'#łosoś')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(4,'#sushi')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(4,'#awokado')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(4,'#mango')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(4,'#tuńczyk')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'łosoś',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'łosoś wędzony',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'mango',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'awokado',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'ananas',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'paluszki surimi',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'ogórek',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'szczypiorek',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'sałata',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'krewetki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(4,'sezam',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(4,1,'Przygotujcie sobie wszystkie składniki. Ja kroję mango, awokado, podsmażam lub piekę rybę. Do ryby, na koniec pieczenia lub smażenia warto dodać 2-3 łyżki sosu teriyaki dzięki temu zyska fajnej słodyczy. Tutaj siekam drobno łososia, doprawiam cytryną, chili i dodaję posiekanego pora.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(4,2,'Ryż układam na algach, na to dodatki. To takie europejskie sushi, czy też bardziej amerykańskie, jak california sushi :) Chodzi o składniki, możecie zrobić z jednym lub kilkoma.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(4,3,'Roluję ścisło. Im ściślej tym lepiej. Matą możecie nadać kształt kwadratu, a nawet trójkąta.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(5,'Łosoś w nori z sałatką','Bardzo smaczne danie, które podbiło moje kubki smakowe. Łosoś w nori z warzywną sałatką to pomysł na podanie łososia w innej formie niż sushi czy pieczone ryba. Bardzo atrakcyjnej. Łosoś jest w marynacie a potem otoczony panierką z grubo utartego pieprzu i owinięty nori.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(5,'#sushi')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(5,'#przystawki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(5,'#dania rybne')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'ok 400 g łososia',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'2 łyżki zielonego pieprzu',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'2 łyżki soli',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'2 łyżki cukru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'sok z całej limonki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'2 cm imbiru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'2 łyżeczki sosu rybnego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'nori 2 płaty',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'trochę wody',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'sos rybny',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'1 łyżeczka cukru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'sok z połowy limonki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'pół trawy cytrynowej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'kawałek chili',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'kilka sztuk szparagów',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'pół ogórka',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'1 mała cebula czerwona',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'kiełki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'10 pomidorków koktajowych',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(5,'szczypior',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(5,1,'Mieszam sól, cukier, sok z limonki, sos i posiekany drobno imbir i taka marynatą nacieram rybę. Odstawiam na 2 godziny do lodówki, potem ścieram mokrą dłonią nadmiar (to ważne, bo mamy tu sporo soli itd.).')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(5,2,'Warzywa surowe kroję ukośnie w paseczki. Im cieńsze tym ładniej całość wygląda.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(5,3,'Nori kruszę, zalewam woda, sokiem z limonki, doprawiam cukrem, sosem i blenduję na pastę.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(5,4,'Łososia dzielę na pasy, obtaczam w rozbitym zielonym pieprzu (ja ucieram w moździerzu) i mocno zawijam w folię by kawałki miały ładny kształt w przekroju.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(5,5,'Odwijam, kładę na nori, roluję i odkładam na chwilę. Po czym ostrym nożem kroję na kawałki, jak sushi. Nakładam puree nori, na to sałatkę i kawałki łososia. Smacznego.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(6,'Homar – jak jeść homara','Zobacz jak jeść homara, od czego zacząć, jakie części homara są jadalne, które są najsmaczniejsze. Homara możesz przygotować w domu, a możesz wybrać w restauracji, tak czy tak w poradniku zobaczysz krok po kroku, jak jeść.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(6,'#nauka gotowania')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(6,'#homar')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(6,'homar',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(6,'sól',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(6,'woda',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,1,'Najpierw oddziel sprawnym ruchem ogon od korpusu.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,2,'Zauważysz, że ogon będzie zagięty. Na zdjęciu widać również czerwoną ikrę, która jest również rarytasem. Czerwona ikra jest u osobników żeńskich, a u męskich jest homarowa wątróbka, kremowo-zielona, dość intensywna.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,3,'Teraz oddziel szczypce, ruchem \"odkręcającym\".')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,4,'Tak samo oddziel we wszystkich stawach.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,5,'Mięso możesz wypchnąć lub wyjąć widelczykiem.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,6,'A szczypce najlepiej potraktować specjalnymi szczypcami.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,7,'Wyglądają jak większy dziadek do orzechów, łatwiej będzie wyjąć mięso ze środka, a jest go tam sporo.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,8,'Widelczykiem wyjmij mięso.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,9,'Ogon musisz rozgiąć.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,10,'I ostrożnie ostrym nożem naciąć w połowie. Uważaj by nóż Ci się nie ześlizgnął, zacznij od połowy i delikatnie natnij.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,11,'Teraz dłońmi rozszerz, mięso w zasadzie samo wyskoczy.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(6,12,'Przepłucz, wyjmij żyłkę (taka ciemna - poznasz) i możesz jeść. Możesz skropić cytryną, polać masłem. Z mniejszych elementów możesz mięso wysysać.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(7,'Homar – jak przygotować homara','Zobacz, jak przygotować homara, ile czasu należy go gotować, na co zwrócić uwagę kupując homara i jak należy go doprawić. W tym wpisie znajdziesz wszystkie potrzebna informacje na temat tego jak przygotować, jak gotować homara.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(7,'#nauka gotowania')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(7,'#homar')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(7,'homar',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(7,'woda',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(7,'sól',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(7,1,'Jeśli masz mrożonego homara to musisz go rozmrozić. Albo zostaw go na całą noc w lodówce albo możesz włożyć na kilka godzin z miski z chłodną wodą.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(7,2,'Homara umieść w garnku z gotującą, osoloną wodą głową w dół i gotuj od 3-5 minut od ponownego zawrzenia wody. Nie gotuj za długo!')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(7,3,'Ugotowanego homara umieść na 5-7 minut w zimnej osolonej wodzie, łatwiej odejdzie mięso od skorupki.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(7,4,'Teraz możesz przejść d kolejnej części poradnika, czyli jak jeść homara.')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(8,'Ryba z karmelizowaną dynią','Pomysł na smaczny, szybki obiad, którym można zaskoczyć domowników. Dynia bez pieczenia, czy specjalnego gotowania, ot karmelizowana. Jako dodatek do łososia. Dyni nadałam lekko rozmarynowy smak, świetnie pasuje do ryby. A całość podałam z przesmażonym na maśle jarmużem.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(8,'#łosoś')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(8,'#jarmuż')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(8,'#dynia')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(8,'#dania rybne')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(8,'600 g łososia filet',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(8,'1 łyżka żurawiny',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(8,'sól, pieprz do smaku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(8,'1 łyżka oliwy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(8,'1 łyżka cukru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(8,'1/2 szklanki wody lub herbaty zielonej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(8,'1/2 łyżeczki rozmarynu lub pół gałązki świeżego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(8,'ok. 250 g dyni surowej',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(8,1,'Dynię obieram i kroję w kostkę.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(8,2,'Cukier podgrzewam na suchej patelni aż się rozpuści. Nie mieszam. Dodaję dynię, chwilę mieszam, zalewam herbatą i zostawiam na małym ogniu.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(8,3,'Rybę grilluję, a na sam koniec smaruję oliwą wymieszana z żurawiną.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(8,4,'Do dyni dorzucam rozmaryn i podgrzewam aż większość płynu odparuje. Podaję do ryby.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(9,'Łosoś na patyczkach – danie na imprezę','Nie wiecie, jakie przygotować dania na imprezę? Polecam tzw. finger food, dania na jeden kęs lub takie, które można chwycić w dłoń i dobrze bawić się dalej. Tym razem proponuję łososia na patyczkach. Świetny na zimno i na ciepło, to rodzaj szaszłyków z ryby, tylko w wersji lekko słodkiej, w fantastycznej marynacie, więc posmakuje nawet tym, którzy za rybą nie przepadają (zresztą jak chcecie, to zróbcie to danie np. z kurczaka). Prócz łososia, na patyczki nadziałam suszone śliwki (rewelacyjny duet z łososiem) i truskawki.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(9,'#łosoś')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(9,'#dania rybne')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(9,'#potrawy sylwestrowe')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(9,'#przekąski karnawałowe')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(9,'#walentynki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(9,'#śliwki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(9,'#truskawki')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'ok. 1 kg łososia',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'1/4 szklanka oliwy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'2 łyżki dżemu porzeczkowego, np. Fine Food',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'1 łyżka miodu',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'1 łyżka sosu sojowego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'1 łyżeczka pieprzu',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'ew. 1/2 łyżeczki chili',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'1–2 łyżki keczupu',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'1–2 łyżki majonezu, np. Fine Food',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'kilkanaście suszonych śliwek, np. BIO Fine Food',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(9,'truskawki',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(9,1,'Do szaszłyków rybnych proponuję świeżego łososia, ale możecie też wykorzystać kurczaka albo inną rybę o zwartym mięsie (najlepiej świeżą).')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(9,2,'Łososia obieram ze skóry i kroję w paseczki.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(9,3,'Przygotowuję marynatę z oliwy, dżemu, sosu sojowego, miodu i przypraw. Wkładam łososia i odstawiam do lodówki na 30 minut.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(9,4,'Nadziewam na patyczki, tworząc takie \"wężyki\". Wstawiam do nagrzanego do 180°C piekarnika i piekę ok. 20 minut, aż marynata się zrumieni.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(9,5,'Na czubek każdego patyczka nabijam suszoną śliwkę lub kawałek truskawki. Do tego podaję sos (zmieszany majonez z keczupem). Dobre na ciepło i zimno, smacznego. ;-)')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(10,'Przystawka z surimi','Szybka przystawka z surimi, czyli paluszków o smaku krabowym. Ciekawa propozycja na święta lub sylwestrową zabawę. Majonezowa sałatka podana z kawiorem. Niby niewiele, a świetnie sprawdza się jako dodatek na stole. Możecie też podawać z tostami – smakuje świetnie!',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(10,'#potrawy sylwestrowe')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(10,'#potrawy wigilijne')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(10,'#sałatka na kolację')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(10,'ok. 300 g paluszków surimi',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(10,'50 g kawioru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(10,'3 łyżki majonezu',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(10,'3 łyżki ubitej śmietany',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(10,'odrobina świeżego tymianku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(10,'sól i pieprz cayenne',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(10,1,'Przygotowanie zaczynam od obrania paluszków (wystarczy poturlać w dłoniach!) i kroję pod skosem na 1–2 cm kawałki.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(10,2,'Dodaję majonez, śmietanę, doprawiam i mieszam. Nakładam na talerze, na wierzch kładę kawior. Smacznego.')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(11,'Pancake z owocami na gorąco','Przepis na słodkie śniadanie, czyli pancake z owocami na gorąco, jogurtem oraz czekoladą. Pyszne, szybkie i sycące. Chciałam zrobić alternatywne do męskiego śniadania śniadanie na słodko, takie, które może również uchodzić za pyszny deser.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(11,'#placki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(11,'#śniadanie')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(11,'#czekolada')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'2 jajka',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'2 szklanki mąki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'2 szklanki wody',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'2 łyżki oleju',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'3–4 łyżki cukru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'2 łyżeczki proszku do pieczenia',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'500 g owoców np. wiśni',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'1 łyżka mąki ziemniaczanej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'1/3 szklanki wody',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'2–4 łyżek cukru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'jogurt ok. 150 g',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(11,'pół tabliczki czekolady',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(11,1,'Przygotowanie zaczynam od ciasta. Wrzucam do miski składniki i mieszam.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(11,2,'Patelnię rozgrzewam, przecieram papierem z kroplą oliwy i nakładam łyżkę wazową ciasta. Smażę na średnim ogniu, aż pojawią się bąbelki-dziurki, wtedy odwracam.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(11,3,'W tym czasie owoce wrzucam do garnka, dodaję ciut wody (chyba, że to owoce mrożone, to wrzucam razem z sokiem z mrożenia), dodaję cukier i gotuję. Mąkę ziemniaczaną rozrabiam z wodą i dodaję do owoców. Podgrzewam, aż zrobi się szkliste, ale uważam, by zbytnio nie zgęstniało.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(11,4,'Czekoladę siekam. Podaję pankejki z jogurtem, wiśniami i czekoladą. Smacznego.')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(12,'Placki z jabłkami najlepsze','Przepis na najlepsze placki z jabłkami, puszyste, miękkie w środku, a z zewnątrz delikatnie chrupiące. Placki z jabłkami do smaki, jakie zapamiętałam z domu rodzinnego. Pamiętam to dobrze, wracałam ze szkoły, a wieczorem tata lub mama stali przy kuchence i smażyli jedne za drugimi placki z jabłkami. Próbowało się jeszcze gorące choć każdy wiedział, że mogą oparzyć, ale te gorące były najlepsze!',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(12,'#dla dzieci')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(12,'#placki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(12,'#placki z jabłkami')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(12,'#jabłka')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(12,'2 szklanki mąki (ok. 310-320 g)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(12,'niecałe 2 szklanki mleka (może być roślinne, tj. ok. 480 ml)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(12,'2 jajka',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(12,'1 łyżka cukru waniliowego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(12,'1 łyżeczka proszku do pieczenia',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(12,'2-3 duże jabłka',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(12,'1 łyżka oleju',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(12,'olej do smażenia',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(12,'cukier puder do posypania',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(12,1,'Mąkę, cukier, jajka, mleko i proszek do pieczenia oraz olej mieszam na gładką masę.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(12,2,'Jabłka kroję na plasterki (nie za cienkie!) i po kilka wrzucam do miski z ciastem.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(12,3,'Nie wrzucaj wszystkich za jednym razem aby ciasto nie zrobiło się zbyt lejące.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(12,4,'Na rozgrzany tłuszcz nakładam łyżką porcję ciasta z jabłkiem i smażę na średnim ogniu aż będą złociste.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(12,5,'Obracam na drugą stronę i jeszcze smażę, by również od spodu były zrumienione. Podaję posypane cukrem pudrem.')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(13,'Różowe pancakes z burakiem','Moje dzieci oszalały na punkcie kolorowych pancakes, tym razem zrobiłam różowe pancakes z buraczkiem. Bez sztucznych barwników, jedynie zmiksowany burak :) Pancakes z buraczkiem to puszyste placuszki, które najlepiej smakują z syropem klonowym czy cukrem pudrem - w wersji na słodko.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(13,'#placki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(13,'#dla dzieci')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(13,'#śniadanie')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(13,'#buraki')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(13,'1 niewielki burak lub 1/2 większego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(13,'ok. 390-400 ml maślanki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(13,'280 g mąki pszennej jasnej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(13,'2 jajka',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(13,'1-2 łyżki cukru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(13,'3 łyżki oleju',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(13,'1 płaska łyżeczka proszku do pieczenia',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(13,1,'Buraka miksuję z maślanką aż będzie gładki mus. Pieczony burak ładniej się miksuje niż surowy, ale spokojnie ewentualne grudki nie są wyczuwalne :)')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(13,2,'Dodaję pozostałe składniki. I delikatnie łączę.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(13,3,'Na suchą, rozgrzaną patelnię, nakładam porcję ciasta i smażę na małym ogniu. Zbyt wielki ogień to przypalone placki, im mniejszy tym placki ładniejsze :) Jak zaczną pojawiać się dziurki to obracam i smażę z drugiej strony. Gotowe :)')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(14,'Placki orzechowe z bananami','Pomysł na smaczne śniadanie - placki orzechowe z bananami. Proste, smaczne (ba! bardzo smaczne!) i zdrowsze :). Coś dla tych z Was, którzy unikają glutenu, również dla młodszych domowników, którzy lubią słodkie, ale niekoniecznie z cukrem :)',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(14,'#bezglutenowe')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(14,'#placki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(14,'#śniadanie')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(14,'90 g orzechów nerkowców',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(14,'80 g jogurtu naturalnego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(14,'1 jajko',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(14,'1 banan',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(14,'1 łyżka oleju rzepakowego lub innego',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(14,1,'Orzechy mielę w młynku do kawy lub maszynce do mięsa. Tak by były zmielone na mąkę.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(14,2,'Miksuję banana z jogurtem, jajkiem, olejem, dodaję mąkę orzechową. Mieszam.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(14,3,'Nakładam na patelnię (bez tłuszczu) i smażę na małym-średnim ogniu aż będą rumiane. Smacznego.')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(15,'Delikatny czekoladowy mus bez jajek','Pokażę przepis na pyszny czekoladowy mus bez jajek, wegański, delikatny. O fantastycznej strukturze, pyszny! Możesz mus czekoladowy modyfikować wedle uznania dodając owoce czy dosładzając. Jest prosty do przygotowania, ze składników, które są łatwo dostępne :)',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(15,'#bez jajek')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(15,'#desery na zimno')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(15,'#wegańskie desery')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(15,'#czekolada')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(15,'150 ml wody po ciecierzycy (tyle jest w puszce 400 g ciecierzycy)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(15,'100 g czekolady gorzkiej (tj. kostka)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(15,'1 łyżeczka cukru z wanilią',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(15,'opcjonalnie ulubione dodatki',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(15,1,'Wodę po ciecierzycy ubijam na puch, możesz dodać odrobinę cukru jeśli lubisz słodsze desery. Mi ubicie zajmuje ok. 6-7 minut.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(15,2,'Czekoladę rozpuszczam w kąpieli wodnej, czyli wrzucam do miski połamaną czekoladę i tę miskę ustawiam na garnku z zagotowaną wodą. Co jakiś czas mieszam czekoladę.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(15,3,'Jeśli masz mieszadło do delikatnego łączenia to możesz czekoladę dodać do ubitej piany, ale jeśli dysponujesz szpatułką to polecam pianę dodawać do czekolady. Trochę może klapnąć, ale nie martw się. Potem przekładam do miseczek i chłodzę w lodówce minimum 1 godzinę (najlepsze po kilku) :)')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(16,'Brownie najlepsze','Przepis na najlepsze brownie, idealne. Wilgotne, mocno czekoladowe, wytrawne, niesamowicie smaczne. Brownie najlepsze to ciasto dla każdego łasucha, do tego filiżanka kawy i poczujesz się bajkowo, bo to pyszne jest! Ja brownie uwielbiam, jest to ciasto nieziemsko czekoladowe, takie, jakie lubię najbardziej.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(16,'#czekolada')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(16,'#sposób na pms')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(16,'#murzynki i brownies')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(16,'#kuchnia amerykańska')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(16,'#brownie')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(16,'200 g masła',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(16,'200 g czekolady deserowej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(16,'1/2 szklanki cukru trzcinowego lub zwykłego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(16,'3 jajka',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(16,'130 g mąki pszennej jasnej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(16,'opcjonalnie garść bakalii',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(16,'50 g czekolady',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(16,'50 g śmietanki 30%',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(16,1,'Czekoladę rozpuszczam razem z masłem w kąpiel wodnej, czyli do miski wrzucam czekoladę i masło i ustawiam na rondelku z gorącą wodą. Co jakiś czas mieszam.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(16,2,'Jajka roztrzepuję razem z cukrem, nie za mocno, mają tylko delikatnie się napuszyć. Do tego dodaję mąkę.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(16,3,'A potem masę czekoladową, które zdążyła odrobinę przestygnąć.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(16,4,'Masa będzie gęsta.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(16,5,'Formę ok. 24x30 cm wykładam papierem do pieczenia i wykładam ciasto. Piekę ok. 20 minut w 180C, jak wolisz mocniej wypieczone to 25 minut, ja wolę bardziej wilgotne w środku stąd krócej. Czekoladę na polewę rozpuszczam w kąpieli wodnej i mieszam ze śmietanką i taką polewą smaruję ciasto.')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(17,'Rolada czekoladowa z wiśniami','Przepyszna rolada czekoladowa z wiśniami w dodatku rolada w serduszka. Pięknie prezentuje się na stole i fantastycznie smakuje. Wzór serduszek zrobiłam moim opracowanym sposobem do robienia wzorków na biszkoptach, wcale nie ma z tym dużo pracy za to efekt jest świetny. Za to samą roladę nadziałam kremem a w zasadzie musem czekoladowym i wiśniami. Jest pyszna!',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(17,'#czekolada')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(17,'#walentynki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(17,'#ciasto rolada')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(17,'#wiśnie')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'90 g masła miękkiego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'90 g cukru pudru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'90 g mąki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'1 białko',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'2 łyżki kakao',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'3 jajka',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'1 szklanka mąki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'1/2 szklanki cukru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'1/3 szklanki wody',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'1 łyżeczka cukru z wanilią',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'250 g śmietany kremówki 36%',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'ok. 180 g czekolady gorzkiej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'1/2 szklanki cukru pudru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(17,'1 szklanka wiśni (mogą być mrożone) + 3 łyżki cukru',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(17,1,'Zaczynam od masy dekoracyjnej (można zrobić bez tego oczywiście). Masło ubijam z cukrem pudrem, dodaję białko, mąkę, kakao i jak już jest zmiksowane to napełniam szprycę i na papierze do pieczenia rysuję wzór. Chowam do lodówki na kilkanaście minut. Uwaga masy starczy na dwie rolady.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(17,2,'Teraz biszkopt. Białka ubijam z cukrem na sztywna pianę, dodaję żółtka, mąkę, wodę i cukier z wanilią i delikatnie łączę. Nie trzeba dodawać proszku do pieczenia, choć można, ale wtedy może być za gruba :) Wykładam ostrożnie na wzorek do formy. Moja forma to ok. 40 x 28 cm. Wstawiam do nagrzanego do 200°C piekarnika i piekę ok. 17 minut.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(17,3,'Gorącą roladę przekładam na ściereczkę, ostrożnie odrywam papier.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(17,4,'A potem przekładam i zwijam (serduszka muszą być na zewnątrz). I w takie zwiniętej (nie za ścisło!) postaci odstawiam do wystudzenia.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(17,5,'Wiśnie wrzucam do garnka, dodaję odrobinę wody i cukier i podgrzewam aż wiśnie zmiękną.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(17,6,'Ubijam śmietanę z cukrem a czekoladę topię w kąpieli wodnej (poczytajcie o topieniu by nie przegrzać!). Do ubitej śmietany dodaję czekoladę. Ostrożnie!')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(17,7,'Szybko miksuję aby nie przebić.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(17,8,'Sokiem z gotowania wiśni nasączam roladę. Nakładam krem głównie na środek, na to wiśnie.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(17,9,'Roluję, poprawiam krem na brzegach i chowam do lodówki. Kroję ostrym nożem :) Smacznego! ')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(18,'Pizza na spodzie jaglanym (bezglutenowa)','To jedna z moich ulubionych wersji pizzy bezglutenowej - pizza na jaglanym spodzie. Chrupiąca, smaczna, prosta do przygotowania. Dzisiejsza pizza jest również pizzą wegańską, ale możesz dobrać dodatki wedle własnych upodobań.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(18,'#pizza')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(18,'#pizza bezglutenowa')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(18,'#pizza i focaccia')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(18,'#pizza wegańska')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(18,'#ananas')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(18,'#kasza')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(18,'#pomidory')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'180 g kaszy jaglanej + 400 ml wody',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'1 łyżka zmielonego siemienia lnianego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'2 łyżki oliwy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'3-4 łyżki wody',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'2-3 łyżki mąki kukurydzianej lub innej bezglutenowej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'szczypta soli',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'1 łyżeczka oregano',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'puszka przecieru pomidorowego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'1 łyżka oliwy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'1 łyżeczka oregano',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'sól pieprz - wedle uznania',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'kilka plasterków ananasa',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'4-5 pieczarek',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(18,'cebula, papryka',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(18,1,'Kaszę jaglaną przelewam gorącą wodą, a potem zalewam w garnku ilością wody z przepisu (400 ml), dodaję szczyptę soli i gotuję aż wchłonie płyn. Pod koniec lepiej wyłączyć by doszła niż żeby przypadkiem przypalić.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(18,2,'Miksuję w malakserze z przyprawami, oliwą, wodą, odrobiną mąki na gładko (im mniej grudek tym lepiej).')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(18,3,'Wilgotną dłonią rozprowadzam na kształt koła na blaszce wyłożonej papierem do pieczenia. Zapiekam 15 minut w 200C.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(18,4,'Sos pomidorowy: pomidory przesmażam na oliwie, dodaję przyprawy, czosnek i podgrzewam aż będzie gęsty.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(18,5,'Na podpieczony spód nakładam sos.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(18,6,'A także dodaję dodatki pokrojonę na mniejsze kawałki.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(18,7,'Piekę jeszcze ok. 20 minut, smacznego!')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(19,'Bezglutenowa pizza na spodzie cieciorkowym','Często dostaję prośby by pokazać przepis na bezglutenową pizzę czy też pizzę w wersji fit. Oczywiście wiele osób może się oburzyć, że jak to tak, pizza bez drożdżowego ciasta, pizza bez sera? Niemożliwe! A jednak :) Oto przepis na pizzę na spodzie cieciorkowym, przygotowanym na bazie mąki z ciecierzycy. Jest chrupiąca i bardzo przypomina pizzę klasyczną, szczególnie smakują mi przypieczone ranty.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(19,'#pizza')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(19,'#pizza bezglutenowa')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(19,'#pizza i focaccia')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(19,'#pizza wegańska')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(19,'#bez drożdży')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(19,'#cebula')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(19,'#mąka z ciecierzycy')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(19,'#pomidory')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'100 g mąki z ciecierzycy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'200 ml wody',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'2 łyżki oliwy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'1/2 łyżeczki soli',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'1/2 łyżeczki rozmarynu',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'1/2 łyżeczki oregano',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'szczypta pieprzu',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'1 puszka pomidorów lub 4 pomidory świeże',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'1 cebula',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'1/2 papryki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'ok. 10-12 szt zielonych oliwek',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'1 łyżka oliwy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'1 ząbek czosnku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'garść bazylii do podania',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(19,'1 łyżeczka oregano',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(19,1,'Mieszam dokładnie składniki ciasta, odstawiam na 10 minut i ponownie mieszam. Dzięki temu mąka z ciecierzycy lepiej się rozpuści (bez grudek) i całe ciasto się trochę \"napuszy\". Formę ok. 28 cm smaruję olejem i wylewam ciasto (płynne). Piekę ok. 15-20 minut w 220-250C (im wyższa temperatura tym lepiej), aż będzie rumiane.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(19,2,'Ciasto powinno ładnie odchodzić, ja piekę z termoobiegiem. Ciasto wyjmuję, przekładam na blaszkę. A pomidory kroję, przekładam na patelnię i przesmażam na oliwie. Dodaję sól, pieprz, oregano i czosnek i mieszam aż nie będą takie płynne i większość sosu odparuje.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(19,3,'Wykładam sos na mój spód, kładę cebulę, oliwki, paprykę, posypuję oregano (opcja) i zapiekam w 200C ok. 15-20 minut, aż wierzch się zrumieni. Smacznego.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(20,'Pizza z patelni (bez drożdży)','Przepis na najlepszą pizzę z patelni, która smakuje niemal tak, jak pieczona włoska pizza (naprawdę!), a do zrobienia jest w kilka chwil. Ciasto nie musi wyrastać, a całość możecie zrobić na patelni. Jest to przepis bez drożdży! I naprawdę wierzcie mi, pizza wychodzi cienka, chrupiąca, delikatna - idealna!',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(20,'#pizza i focaccia')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(20,'#szybki obiad')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(20,'300 g mąki pszennej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(20,'3 łyżki oliwy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(20,'1 łyżeczka proszku do pieczenia',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(20,'1 łyżeczka ziół prowansalskich',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(20,'sól do smaku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(20,'puszka przecieru pomidorowego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(20,'1 łyżka oliwy',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(20,'zioła prowansalskie, sól pieprz - po szczypcie',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(20,'100 g wędliny drobiowej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(20,'200 g mozzarelli',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(20,1,'Wszystkie składniki na ciasto na pizze mieszam, a potem wyrabiam. Ma konsystencję podobną do pierogowego ciasta. W tym czasie robię sos podsmażając przecier pomidorowy z oliwą i przyprawami.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(20,2,'Dzielę na 3 części, bo będą z tego 3 pizze.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(20,3,'Wałkuję na blacie podsypanym mąką.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(20,4,'Patelnię (zimną) smaruję lekko olejem lub oliwą i kładę rozwałkowany płat. Stawiam na ogniu i podsmażam, aż od spodu będzie zrumieniona. Wtedy obracam i smaruję sosem.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(20,5,'Nakładam dodatki i przykrywam pokrywą. Tak smażę aż roztopi się ser, a dół będzie rumiany. Podaję póki gorąca!')");


        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(21,'Grillowana pierś kurczaka z mozzarellą','Pyszne, aromatyczne kotlety z kurczaka, grillowane z kawałkami mozzarelli i pomidorów. Są rewelacyjne i proste do przygotowania. Możecie je zrobić zarówno na prawdziwym grillu w terenie, jak i na domowym grillu elektrycznym.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(21,'#dania z drobiu')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(21,'#grill')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(21,'#kotlety')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(21,'#mozzarella')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(21,'#pomidory')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(21,'#dla karmiących')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(21,'100 g mozzarelli',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(21,'pierś kurczaka ok. 500 g',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(21,'1 pomidor',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(21,'sól',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(21,'czarnybazylia',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(21,'pieprz',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(21,'troszkę oliw',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(21,1,'Pierś kurczaka dzielę na mniejsze części i rozbijam tłuczkiem. Smaruję oliwą i posypuję pieprzem oraz solą.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(21,2,'Układam na rozgrzanym grillu, po grillowaniu jednej strony obracam, układam mozzarellę i pomidory, a z wierzchu posypuję bazylią.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(21,3,'Grilluję, aż mozzarella się rozpuści, a kotlety będą rumiane.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(22,'Rogaliki maślane','Przepis na pyszne rogaliki maślane, miękkie, puszyste, pachnące domowym ciepłem. Idealne rogaliki maślane na śniadanie. Możesz je rozkroić i posmarować ulubionym dżemem (hmmm np. czekośliwką - rozpusta), a możesz zajadać odrywając po kawałku. Rogaliki maślane są tak dobre, że wcale nie wymagają dopełnienia.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(22,'#chleb na drożdżach')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(22,'#śniadanie')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(22,'#rogaliki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(22,'#bułki i bułeczki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(22,'#pieczywo pszenne')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(22,'#proste przepisy na chleb')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(22,'ok. 600 g mąki pszennej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(22,'250 ml wody',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(22,'60 g masła roztopionego',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(22,'2 łyżki cukru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(22,'2 jajka',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(22,'15 g świeżych drożdży lub 7 g drożdży instant',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(22,'jajko do posmarowania',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(22,'ok. 40 g masła do smarowania (roztopionego)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(22,'sezam do posypania lub mak',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(22,1,'Masło rozpuszczam w rondelku, ja rozpuszczam 100 g, z tego 60 g dodaję do ciasta, a reszta zostaje akurat na smarowanie rogalików przed zawijaniem (krok 5).')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(22,2,'Zagniatam ciasto z podanych składników. Przy czym, jeśli używasz drożdży świeżych to te rozpuść w wodzie/mleku (tej ze składników), za to jeśli instant to możesz dodać je bez rozrabiania, bezpośrednio do mąki. Wszystko mieszam a potem zagniatam ok. 10 minut. Ciasto odstawiam na ok. 1 godzinę.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(22,3,'Powinno podwoić objętość, to jest ważniejsze niż czas.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(22,4,'Dzielę na dwie części i każdą wałkuję na kształt koła.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(22,5,'Tnę na trójkąty (jak pizze). Każdy trójkąt smaruję masłem.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(22,6,'I zwijam do środka. Brzegi lekko zaginam tworząc rogalik.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(22,7,'Odkładam na 30-40 minut by podrosły, a potem smaruję rozkłóconym jajkiem i posypuję sezamem lub makiem. Piekę w 180C przez ok. 16-20 minut aż się zrumienią.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(23,'Rogaliki śniadaniowe','Przepis na przepyszne rogaliki śniadaniowe z makiem, miękkie, drożdżowe, idealne. Uwielbiam przygotowywać rogaliki śniadaniowe razem z dziećmi, wałkowanie, rolowanie i formowanie małych półksiężyców to wielka frajda dla najmłodszych. Rogaliki pięknie rosną, są miękkie i idealne do podjadania.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(23,'#proste przepisy na chleb')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(23,'#bułki i bułeczki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(23,'#śniadanie')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(23,'#rogaliki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(23,'#dla dzieci')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(23,'#rogaliki i drożdżówki')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(23,'600-620 g mąki pszennej (prawie 4 szklanki)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(23,'200 ml wody (możesz dać pół na pół z mlekiem)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(23,'90 ml oleju',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(23,'7 g drożdży ionstant lub 15 g świeżych drożdży',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(23,'2 jajka',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(23,'4 łyżki cukru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(23,'szczypta soli',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(23,'mak do posypania',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(23,'rozkłócone jajo do pormarowania',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(23,1,'Wszystkie składniki umieszczam w misce. Jeśli używam drożdży świeżych to wcześniej je mieszam w wodzie, jak suszonych to dodaję bezpośrednio.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(23,2,'Ciasto wyrabiam ok 10 minut aż będzie elastyczne.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(23,3,'Odkładam do miski, przykrywam i zostawiam na godzinę by podwoiło objętość.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(23,4,'Wyrośnięte ciasto wyjmuję na omączony blat i dzielę na 2-3 części.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(23,5,'Każdą część wałkuję na koło')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(23,6,'i tnę jak pizzę na 6-8 kawałków')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(23,7,'Roluję, nacinam i roluję do końca nadając kształt rogalika.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(23,8,'Odstawiam na blaszce na 30 minut by podrosły.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(23,9,'Smaruję rozkłóconym jajkiem i posypuję makiem.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(23,10,'Piekę w 180C przez 15-20 minut aż będą wyraźnie rumiane. Dużo zależy od tego czy zrobisz mniejsze czy większe dlatego zwróć uwagę na to czy są rumiane, ja lubię piec je w opcji z termoobiegiem.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(24,'Puszyste bułeczki z Nutellą','Dzisiaj chcę Wam pokazać prosty sposób na smaczne bułeczki, oczywiście dla zabieganych. Bułeczki robi się szybciutko, wszak to moja lekka kombinacja na temat moich ulubionych puszystych bułeczek, tyle, że tym razem są to bułeczki nadziane Nutellą.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(24,'#chleb na drożdżach')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(24,'#bułki i bułeczki')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(24,'#czekolada')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(24,'#pieczywo pszenne')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(24,'#pieczywo słodkie')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(24,'570 g mąki pszennej jasnej',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(24,'12 g drożdży instant lub 24 g drożdży świeżych',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(24,'175 ml mleka + 10 ml do drożdży',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(24,'85 g masła',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(24,'4 łyżeczki cukru',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(24,'2 jajka',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(24,'łyżeczka soli',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(24,'rozkłócone jajko do smarowania przed pieczeniem',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(24,'Nutella',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(24,1,'Drożdże mieszam z mlekiem. A następnie wszystkie składniki umieszczam w misce i mieszam. Przekładam na blat i zagniatam, aż ciasto będzie gładkie.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(24,2,'Odstawiam pod ściereczką na 1 h wyrastania. Po tym czasie ciasto powiększy swoją objętość, a ja wyjmuję z miski, chwilkę wyrabiam i dzielę na 12 części − tyle, ile bułeczek.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(24,3,'Z każdej bułeczki formuję kulkę. Uwaga, to jest moment, gdy można nadziać bułeczki Nutellą, aby piekły się z nadzionkiem. Rozpłaszczam kawałek ciasta na placuszek, kładę łyżkę Nutelli i zlepiam rogi jak sakiewce. Jeśli wolicie nadziewać Nutellą po pieczeniu, to przyjdzie na to czas na sam koniec, a teraz tylko uformujcie kulki.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(24,4,'Odstawiam do wyrośnięcia na 30 minut (na nadziane), a na troszkę dłużej te puste (max 1 h).')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(24,5,'Smaruję wyrośnięte bułeczki rozkłóconym jajkiem. Wstawiam do nagrzanego piekarnika do 190°C na 15 minut.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(24,6,'Jeśli nie nadzialiście bułek w kroku 3, to teraz po upieczeniu jest na to czas. Biorę szprycę lub dużą strzykawkę, napełniam Nutellą i wbijam w bułkę (najlepiej z boku) i naciskam spust, powolutku wyciągając szprycę (będzie ładnie nadziana). Gotowe :) Smacznego!')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(25,'Spaghetti z dyni','Pyszny makaron warzywny, czyli spaghetti z dyni z sosem pomidorowym. To niesamowite, jak z jednej dyni makaronowej po upieczeniu miąższ przemienia się na tysiące makaronowych nitek. Takie spaghetti z dyni lubię podawać z sosem pomidorowym. To pomysł na smaczny, szybki, zdrowy i wegańskim obiad.',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(25,'#dieta warzywno-owocowa')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(25,'#przepisy wegańskie')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(25,'#pomidory')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(25,'#dynia')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(25,'#szybki obiad')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(25,'1 dynia makaronowa',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(25,'2 puszki pomidorów w puszce',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(25,'4 ząbki czosnku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(25,'1,5 łyżeczki oregano',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(25,'1 łyżeczka słodkiej papryki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(25,'1 łyżka oliwy (opcjonalnie)',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(25,'sól, pieprz wedle uznania',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(25,'kilka gałązek natki pietruszki',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(25,'wegański parmezan, parmezan - opcjonalnie',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(25,1,'Dynię przecinam na pół, wyjmuję pestki i wstawiam do nagrzanego do 180C piekarnika, piekę 40 minut. A na patelnię wrzucam posiekany czosnek, pomidory, oliwę (opcjonalnie - nie trzeba dodawać), przyprawy i podgrzewam aż będzie esencjonalny, gęstszy, czerwony.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(25,2,'Upieczoną dynię wyjmuję i delikatnie widelcem oddzielam makaronowe nitki warzywne. Przekładam do miski.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(25,3,'Doprawiam solą, pieprzem, dorzucam posiekaną natkę pietruszki, mieszam. Nakładam na talerz razem z sosem pomidorowym. Z wierzchu można posypać serem wegańskim lub parmezanem jeśli używacie.')");

        db.execSQL("INSERT INTO recipes(id,name,short_description,mark) values(26,'Makaron z cukinii z kremowym sosem awokado','',0)");
        db.execSQL("INSERT INTO tags(rec_id,name) values(26,'#szybki obiad')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(26,'#bezglutenowe')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(26,'#awokado')");
        db.execSQL("INSERT INTO tags(rec_id,name) values(26,'#bazylia')");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(26,'1 cukinia',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(26,'1 miękkie awokado',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(26,'1-2 ząbki czosnku',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(26,'sok z połowy cytryny',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(26,'sól, pieprz',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(26,'garść orzechów nerkowców',1)");
        db.execSQL("INSERT INTO products(rec_id,name,product_count) values(26,'bazylia z połowy krzaczka (ok. 2/3 szklanki)',1)");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(26,1,'Miękkie awokado obieram ze skórki.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(26,2,'Wrzucam do blendera razem z bazylią, sokiem z cytryny, przyprawami, czosnkiem, orzechami i oliwą. Blenduję na gładką pastę.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(26,3,'Przygotowuję makaron z cukinii.')");
        db.execSQL("INSERT INTO steps(rec_id,number_of_step,description) values(26,4,'W misce mieszam makaron z sosem tak aby każda nitka była oblepiona sosem. Podaję.')");
        //
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
