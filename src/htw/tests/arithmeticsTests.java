package htw.tests;



import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import htw.curves.BasicTheoreticMethods;

public class arithmeticsTests {
	
		
	BasicTheoreticMethods test = new BasicTheoreticMethods();
	
				
	@Test
	public void testModCalculation() {
							
		assertEquals(BigInteger.ZERO, test.modCalculation(BigInteger.valueOf(0), BigInteger.valueOf(3)));
		assertEquals(BigInteger.valueOf(6), test.modCalculation(BigInteger.valueOf(15), BigInteger.valueOf(9)));
		assertEquals(BigInteger.ZERO, test.modCalculation(BigInteger.valueOf(4), BigInteger.valueOf(4)));
		assertEquals(BigInteger.valueOf(9), test.modCalculation(BigInteger.valueOf(9), BigInteger.valueOf(15)));
		assertEquals(BigInteger.ZERO, test.modCalculation(BigInteger.valueOf(-12), BigInteger.valueOf(4)));
		assertEquals(BigInteger.valueOf(8), test.modCalculation(BigInteger.valueOf(-12), BigInteger.valueOf(20)));
		assertEquals(BigInteger.valueOf(2), test.modCalculation(BigInteger.valueOf(-4), BigInteger.valueOf(3)));
		
	}
	
	@Test
	public void whenModuloByZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modCalculation(BigInteger.valueOf(3), BigInteger.ZERO));
	}
	
	@Test
	public void whenModLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.modCalculation(BigInteger.valueOf(5), BigInteger.valueOf(-2)));
	}
	
	@Test
	public void whenNumberAndModAreZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modCalculation(BigInteger.valueOf(0), BigInteger.ZERO));
	}
	
	@Test
	public void testIsCongruent() {
		
		Boolean result_1 = test.isCongruent(BigInteger.valueOf(12), BigInteger.valueOf(20), BigInteger.valueOf(23));
		Boolean result_2 = test.isCongruent(BigInteger.valueOf(45), BigInteger.valueOf(15), BigInteger.valueOf(10));
		Boolean result_3 = test.isCongruent(BigInteger.valueOf(-4), BigInteger.valueOf(5), BigInteger.valueOf(3));
		assertTrue(result_1.equals(false)); 
		assertTrue(result_2.equals(true));
		assertTrue(result_3.equals(true));  
		
	} 
	@Test
	public void testIscongruentWhenModIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.isCongruent(BigInteger.valueOf(3), BigInteger.valueOf(2), BigInteger.ZERO));
	}
	
	@Test
	public void testModAddition() {
				
		assertEquals(BigInteger.valueOf(0), test.modAddition(BigInteger.valueOf(0), BigInteger.valueOf(0), BigInteger.valueOf(5)));
		assertEquals(BigInteger.valueOf(9), test.modAddition(BigInteger.valueOf(11), BigInteger.valueOf(10), BigInteger.valueOf(12)));
		assertEquals(BigInteger.valueOf(8), test.modAddition(BigInteger.valueOf(10), BigInteger.valueOf(-22), BigInteger.valueOf(20)));
	}
	@Test
	public void testModAdditionWhenModIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modAddition(BigInteger.valueOf(3), BigInteger.valueOf(2), BigInteger.ZERO));
	}
	
	@Test
	public void testModSubtraction() {
				
		assertEquals(BigInteger.valueOf(0), test.modSubtraction(BigInteger.valueOf(0), BigInteger.valueOf(0), BigInteger.valueOf(5)));
		assertEquals(BigInteger.valueOf(9),  test.modSubtraction(BigInteger.valueOf(50), BigInteger.valueOf(11), BigInteger.valueOf(15)));
		assertEquals(BigInteger.valueOf(1), test.modSubtraction(BigInteger.valueOf(3), BigInteger.valueOf(50), BigInteger.valueOf(12)));
		assertEquals(BigInteger.valueOf(27), test.modSubtraction(BigInteger.valueOf(14), BigInteger.valueOf(77), BigInteger.valueOf(45)));
		
	}
	
	@Test
	public void testModMultiplication() {
			
		assertEquals(BigInteger.valueOf(0), test.modMultiplication(BigInteger.valueOf(0), BigInteger.valueOf(4), BigInteger.valueOf(5)));
		assertEquals(BigInteger.valueOf(0), test.modMultiplication(BigInteger.valueOf(5), BigInteger.valueOf(0), BigInteger.valueOf(15)));
		assertEquals(BigInteger.valueOf(5), test.modMultiplication(BigInteger.valueOf(74), BigInteger.valueOf(93), BigInteger.valueOf(13)));
		assertEquals(BigInteger.valueOf(16), test.modMultiplication(BigInteger.valueOf(2590), BigInteger.valueOf(5253), BigInteger.valueOf(26)));
		assertEquals(BigInteger.valueOf(7), test.modMultiplication(BigInteger.valueOf(-25), BigInteger.valueOf(14), BigInteger.valueOf(17)));
	}

	@Test
	public void testModMultiplication_Long() {
		BigInteger b1, b2, b3; 
		b1 = new BigInteger("10123465234878998");
		b2 = new BigInteger("65746311545646431");
		b3 = new BigInteger("10005412336548794");
		
		BigInteger res = new BigInteger("4652135769797794");
		assertEquals(res, test.modMultiplication(b1, b2, b3));
	}	
	@Test
	public void testGcdExtended() {
		
	    assertEquals(BigInteger.valueOf(0), test.gcdExtended(BigInteger.valueOf(0), BigInteger.valueOf(0)));
	    assertEquals(BigInteger.valueOf(11), test.gcdExtended(BigInteger.valueOf(11), BigInteger.valueOf(0)));
	    assertEquals(BigInteger.valueOf(6), test.gcdExtended(BigInteger.valueOf(0), BigInteger.valueOf(6)));
	    assertEquals(BigInteger.valueOf(1), test.gcdExtended(BigInteger.valueOf(245), BigInteger.valueOf(459)));
	    assertEquals(BigInteger.valueOf(5), test.gcdExtended(BigInteger.valueOf(-5), BigInteger.valueOf(15)));
	}
	
	@Test
	public void testHasInverse() {
		
		Boolean value_3 = test.hasInverse(BigInteger.valueOf(72), BigInteger.valueOf(35));
		Boolean value_4 = test.hasInverse(BigInteger.valueOf(25), BigInteger.valueOf(65));
		
		assertTrue(value_3.equals(true));
		assertTrue(value_4.equals(false));
		
	}
	@Test
	public void testHasInvWhenNumberIszero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.hasInverse(BigInteger.valueOf(0), BigInteger.valueOf(20)));
	}
	@Test
	public void testHasInvWhenModIsZero_thenArithemticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.hasInverse(BigInteger.valueOf(45), BigInteger.valueOf(0)));
	}
	@Test
	public void testHasInvWhenModIsLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.hasInverse(BigInteger.valueOf(45), BigInteger.valueOf(-3)));
	}
	
	@Test
	public void testMultiplicativeInverse() {
	
		assertEquals(BigInteger.valueOf(0), test.multiplicativeInverse(BigInteger.valueOf(5), BigInteger.valueOf(1)));
		assertEquals(BigInteger.valueOf(1), test.multiplicativeInverse(BigInteger.valueOf(1), BigInteger.valueOf(6)));
		assertEquals(BigInteger.valueOf(4), test.multiplicativeInverse(BigInteger.valueOf(3), BigInteger.valueOf(11)));
		assertEquals(BigInteger.valueOf(19), test.multiplicativeInverse(BigInteger.valueOf(10), BigInteger.valueOf(21)));
		assertEquals(BigInteger.valueOf(5), test.multiplicativeInverse(BigInteger.valueOf(3), BigInteger.valueOf(7)));
		assertEquals(BigInteger.valueOf(4), test.multiplicativeInverse(BigInteger.valueOf(-4), BigInteger.valueOf(17)));
		
		
	}
	@Test
	public void testMulInvWhenNumberIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.multiplicativeInverse(BigInteger.valueOf(0), BigInteger.valueOf(4)));
	}
	@Test
	public void testMulInvWhenModIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.multiplicativeInverse(BigInteger.valueOf(3), BigInteger.valueOf(0)));
	}
	@Test
	public void testMulInvWhenModLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.multiplicativeInverse(BigInteger.valueOf(3), BigInteger.valueOf(-7)));
	}
	
	@Test
	public void testModDivision() {
		
		assertEquals(BigInteger.valueOf(0), test.modDivision(BigInteger.valueOf(0), BigInteger.valueOf(4), BigInteger.valueOf(34)));
		assertEquals(BigInteger.valueOf(2), test.modDivision(BigInteger.valueOf(8), BigInteger.valueOf(4), BigInteger.valueOf(5)));
		assertEquals(BigInteger.valueOf(1), test.modDivision(BigInteger.valueOf(8), BigInteger.valueOf(3), BigInteger.valueOf(5)));
		assertEquals(BigInteger.valueOf(4), test.modDivision(BigInteger.valueOf(11), BigInteger.valueOf(4), BigInteger.valueOf(5)));
	}
	
	@Test
	public void testModDivisionWhenDividendIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modDivision(BigInteger.valueOf(3), BigInteger.valueOf(0), BigInteger.valueOf(8)));
	}
	
	@Test
	public void testModDivisionWhenModLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.modDivision(BigInteger.valueOf(3), BigInteger.valueOf(7), BigInteger.valueOf(-3)));
	}
	
	@Test
	public void testModDivisionWhenModIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modDivision(BigInteger.valueOf(3), BigInteger.valueOf(6), BigInteger.valueOf(0)));
	}
	
	@Test
	public void testModExponentiation() {
		
		assertEquals(BigInteger.valueOf(1), test.modExponentiation(BigInteger.valueOf(4), BigInteger.valueOf(0), BigInteger.valueOf(11)));
		assertEquals(BigInteger.valueOf(0), test.modExponentiation(BigInteger.valueOf(0), BigInteger.valueOf(4), BigInteger.valueOf(5)));
		assertEquals(BigInteger.valueOf(6), test.modExponentiation(BigInteger.valueOf(2), BigInteger.valueOf(5), BigInteger.valueOf(13)));
		assertEquals(BigInteger.valueOf(3), test.modExponentiation(BigInteger.valueOf(3), BigInteger.valueOf(333), BigInteger.valueOf(15)));
		assertEquals(BigInteger.valueOf(8), test.modExponentiation(BigInteger.valueOf(82), BigInteger.valueOf(7), BigInteger.valueOf(20)));
		assertEquals(BigInteger.valueOf(16), test.modExponentiation(BigInteger.valueOf(-15), BigInteger.valueOf(4), BigInteger.valueOf(17)));
		assertEquals(BigInteger.valueOf(7355), test.modExponentiation(BigInteger.valueOf(11444), BigInteger.valueOf(-357), BigInteger.valueOf(48731)));
		
	}
	
	@Test
	public void testModExpWhenModLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.modExponentiation(BigInteger.valueOf(3), BigInteger.valueOf(7), BigInteger.valueOf(-3)));
	}
	
	@Test
	public void testModExpWhenModIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modExponentiation(BigInteger.valueOf(3), BigInteger.valueOf(6), BigInteger.valueOf(0)));
	}
	
	@Test
	public void testPhiWhenNumberLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.phiFunction(BigInteger.valueOf(-10)));
	}
	@Test
	public void testPhiFunction() {

        assertEquals(BigInteger.valueOf(6), test.phiFunction(BigInteger.valueOf(7)));
        assertEquals(BigInteger.valueOf(4), test.phiFunction(BigInteger.valueOf(8)));
        assertEquals(BigInteger.valueOf(4), test.phiFunction(BigInteger.valueOf(10)));
        assertEquals(BigInteger.valueOf(2), test.phiFunction(BigInteger.valueOf(4)));
        assertEquals(BigInteger.valueOf(24), test.phiFunction(BigInteger.valueOf(35)));
        assertEquals(BigInteger.valueOf(120), test.phiFunction(BigInteger.valueOf(143)));
        assertEquals(BigInteger.valueOf(176), test.phiFunction(BigInteger.valueOf(345)));

	}

	@Test
	public void testModMultiplication_Laufzeit() {
		BigInteger b1, b2, b3; 
		b1 = new BigInteger("706815040559628624011655828814189049997822592590769503550716192423011166205520828836107731976949690297488840703257583380646528797232917661044680451640005167029150436168028389322600984356616113304262743319197858036561988262025098890007095475749383188653613190787722053670408721717091637438388716393252963638214049491393378250724723880468067614334658045628068324435101626791712074545803557929774494441421509263501162849669889236033830731279672108019128726351508342463370718726373793685065895297134329785609926224271363504753020784137888874806559121694293845841178426176752264300703556327666866523607227670478820436292550869009350632757122498673581794633439348661621529962139889362965268105885763353739720037826390125701763249307876852241751188530813584172216854433381744806016510186742180273796684275714913269800408712502661474909517078657003399965462305400957654801651284490211160441292900510857999592409802082294747571062377725932167272244630098090292147578773310174185897502773621598853344800632530604600343842821467321248686845994230273696846617844542402552547479244675374940112067899939109688939703874236856672773500374251468568184521381644258930551937144091675150860724401740141808460425181063411269636450025385977162440322865682374398977181561207864185362295787060043825331336516035651675133306865991003080063491325692019309444941725442709419613727168104820991083633502917408548677773475680720445589797970502215576524081412780843871826303908115400970824785943375885705003936496897240921182007856560493960538166074435843326214869070096825502987827415154059751381258819233731293975760031778192639960873098040411665155941936119886309016944822449085722852171963446100550330222062296277481280537639087187914674215978289380759917569444047999318911530773840105355011206437218885086491412478190968877539585616399970924953626081213276966569009134276863088067128050238310470215874989678003207794395743940139792642755192662744948587918455455525607538290180086811844304925690860751570120962199969423751322010238659208365694615931325693188649648524711023745262701542302930856406469102174783426538421920407709008805720068802156927035224746651471271080719509799595952932469726347240120939350532498908883505400900379591290667822063351467955308487085545801411058779137372972053693277136366333851034297367902383696195346174438826405243915496557946004887897953852838641039746436735184184668196396549858304678751112336299544483049448562711671761337043596385266799256225690107926579");
		b2 = new BigInteger("164448180681332856932919221079406875222245392841097816487396445158622479499799434642647441405685104617865576724909860296931750129737767216694383619789025341452240959026117432528709543618224156295506958642510160814938273358619516779384170673413680182305838032148369021449473709603650212890289715456945042625495388261136358474815184086400157910220345573541434405842267628330533110631980035825544962065625468992305435553683877572152876077964115502824760593862714937988061337941253343964870798124056398641971602856386168741346850204419477893561282419626159955911411956570848870505110320227207339694484182877002074704175122335331312606597549997437019159011395275947865958515180597427819417068307454735386255831798798609472760579747003970997332748314872387146222066028569353408289510366836644203558100663550138227170003542324779783323180274479100627563087777643761026894258555157167413497765679861741302521360965468998343773389722583751204323145122802605561626145423798698884461284280788800939176373345946938101644910966929924002096767111042625469154433847319465703014786490409151817076669197668892294188629132220264676510130862384779839199821590182277977417940145107673030037607401625779633199211952655853383847855863131001045017972863223339419951110289905418068855123866694988146597988891553792298990955916002493419799458688639661862912616104628874927134414440062885263803685352616723045058988271795195849619627384092709108711490147755056352581600388037234634022313629615436679568668056496796699324397356717240524335930325619569512662992006056652291352928185912962826364219522002546559814194597218969747944884174920287554023254511854741066723443796550870509238493888852196562823272836938807446407697003046228135406311918274933079219544177621010600739852732928807119314857520983872304165488365547217305388957367872905359424400919139588696785590452962142452382131788928822646256767824519943584772372342595356646238793599250744824827366650534015787661046447120717830558132037372400454202729586606491799122560862313867425098111591832164777324726954620173289643120454190278656548627915316093641943940898015263973528075981007959360241383512113351661621802751365887042466319078007567865685264410068999888707589689573399376701377471716243880538784151146367551112637166614547586791656341560552153345253665070314393525974077775677626353513030548667651308390415341268000350326795644132727210691916369934056251384172259608250030950882336941367837571527130371594781156722562416295329");
		
		b3 = new BigInteger("138102547630171315997311901730542075110921280360005926068554750588340316176608045812226378223623206844863335021549839943626379580028596582919776908841025590525516173388536925007792993850508668686242651389477532581530325008490392305046787095469021265839427530502911410468490981033935199149748308553815251661690898268888774136564193886417843252401253633904224405927165677934723377252428042713446073316254066634554789364308182167721614312804669613192533031712318368089080171752323622863822928893013429621584548427170224384080400521868234307831635853310005581422043269320001278963547161423571817292798160277501706620618970751449650401682633635089262981448000391248684592525578166810338133135598605548763001362426253246719067066405373823256514297533870920989594361362614904898246107309144633513072556772323987566048674725278486849504427244748292610444623941636547193676303946914491666033729595167273681723652806661564437073218654602193285654698052499746975924812177730086072704869255560721591450103933978823720567921710556386726176409270063752784670520023890153836090770504566257501741807894035623458879574659166870635813990518280067721537151096597288910093611764435718488069514646257016870595532374487319535508773536571523707589895665677001414428735760431339162182329572606585001987723837942494116723124252276031743832489760121008392160376839556970708044213878285971477361984494014389940037442565386868489551985305597149374506836007414174008151455122195375450800039704753129226242756284020756385291458700591766803751922044361930713545924973330439057760116877077169642004264221635627203576454143803291892982606392111877980709823085247443169217707666521418989795540281691475966531431323763651821730143486156467398124733590839820392949682141309067460970320718676308988769614786287954400034977924707404607751856774242480278872330182644893118018319476580527975050505448739354482521366151633329079128200811177088986491717621823639489332899707191908427012581020872300060481343636078936232381272675771649342340406639836373960821677221044364341613418517176412863313886940833976878178414315387661256595838212899902437079914509838001157324802028524873820201880936769825900737159661654136040554056570386758027098748348352395291123093370591162476725396763187371413262638974088056067580076505368986300675876848020988524067971878394002548169323256029860550016300093059106365019528103651961437398242647949163973097891644457544894286619653042323065692312564533352316527543985085250999457");	
	
	    BigInteger result = new BigInteger("7258540541574899538562696926010428913640620300368769080783118851089188149183623588898715496443719344933947852152179892438958135041678699270192210430196787684138801123563747237873913060081077177709495130622322397095574003296129824266541109278403327708005834756357143290812921108743385350145989377412933668331513662746900259795451791663960151576021146517477282683519018564357882002821626213626573193497361723841705646882536347395579843624468157639640818194846057784746069296197132588832587208358596520881934414046689604435833946271449634719148747361236532619041526605481918116847951152614236697966210932404286597077917817966966713849820481938954707498886983043539236955758128371532088590120595546916333068792190915885791078275518958870088428695188281107357467098280102804405809670068061374070840496026004546368001598974360899356904256108217307218013498078905825923344975115002753205802067086193365881850342109455898936444594836051783541011443750885684886350884988594945268121405028570943732875665169152127054073671296497192927747860157045688389602033380714677368889911452995835667951128811575079842723693517549229968628372340623884647969838958933132492509436207971191696547523719603488185716405411178663922070659686343020311447134143598599883294653315821208564516502648321874937803330200075434394944711617464966080050164723369516822198699900778704503385767337052926453184439286790342389254408248607204110441173619747840909512568327605659407294689886333970332248966364277237137254273437598266128690360494903561652603080661503831450194778748287220054441820194411359386060609544835609319985418350126434705872035063930085689535738661679852692434555588276661277518151575812260571736705570666188975201548422495721198907666464967770227374635824400522265958934325768322713144530756370883382586463904862057748718939836819035366185104222633681082172893269022455362410300332766647726092377356188399094466586042604806612177804956585911013396713454004632233922685469746643960859136133322713404864765273781310348569507805291926298099917509978520890989237951400523993036697500752581432763920907990148069603522554900440984614364732869110102494391790420949558676591023122462831025577535865034717051306584536553098906355469106240901930860106599372321829175741057599024638869008140646921131749235878851424106366593136623368685412516009326095505182012242220445648288192830171462131316076239889441468745971640695390616877277600822018611524290441653347157244322431980627703150784630834006");
	    assertEquals(result, test.modMultiplication(b1, b2, b3));
	}


	@Test
	public void testCRTwithListOfLengthTwo(){
		ArrayList<BigInteger> list_R = new ArrayList<>(), list_M = new ArrayList<>();
		list_R.add(BigInteger.valueOf(1));
		list_R.add(BigInteger.valueOf(4));
		list_M.add(BigInteger.valueOf(2));
		list_M.add(BigInteger.valueOf(5));

		assertEquals(BigInteger.valueOf(9), test.chineseRemainder(list_R, list_M));
	}
 	
	@Test
	public void testCRTwithListOfLengthThree(){
		ArrayList<BigInteger> list_R = new ArrayList<>(), list_M = new ArrayList<>();
		list_R.add(BigInteger.valueOf(2));
		list_R.add(BigInteger.valueOf(3));
		list_R.add(BigInteger.valueOf(2));
		list_M.add(BigInteger.valueOf(3));
		list_M.add(BigInteger.valueOf(5));
		list_M.add(BigInteger.valueOf(7));

		assertEquals(BigInteger.valueOf(23), test.chineseRemainder(list_R, list_M));
	}
	
	@Test
	public void testCRTwithLargeNumbers(){
		ArrayList<BigInteger> list_R = new ArrayList<BigInteger>(), list_M = new ArrayList<BigInteger>();
		list_R.add(new BigInteger("7631415079307304117"));
		list_R.add(new BigInteger("1248561880341424820456626"));
		list_R.add(new BigInteger("2756437267211517231"));
		list_M.add(new BigInteger("17353461355013928499"));
		list_M.add(new BigInteger ("3882485124428619605195281"));
		list_M.add(new BigInteger("13563122655762143587"));

		assertEquals(new BigInteger("937307771161836294247413550632295202816"), test.chineseRemainder(list_R, list_M));
	}
	
	@Test
	public void testCRTWhenBigIntegerIsNotInvertible_thenArithmeticException(){
		ArrayList<BigInteger> list_R = new ArrayList<>(), list_M = new ArrayList<>();
		list_R.add(BigInteger.valueOf(11));
		list_R.add(BigInteger.valueOf(22));
		list_R.add(BigInteger.valueOf(19));
		list_M.add(BigInteger.valueOf(10));
		list_M.add(BigInteger.valueOf(4));
		list_M.add(BigInteger.valueOf(2));
	
		Assertions.assertThrows(ArithmeticException.class, () -> test.chineseRemainder(list_R, list_M));
	}

	@Test
	public void polynomAdditionTest(){
		ArrayList<BigInteger> list_R = new ArrayList<>(), list_M = new ArrayList<>(), list_Mod = new ArrayList<>(), list_check = new ArrayList<>();
		list_R.add(BigInteger.ONE);
		list_R.add(BigInteger.ONE);
		list_R.add(BigInteger.ZERO);
		list_R.add(BigInteger.ZERO);
		list_R.add(BigInteger.ONE);
		list_M.add(BigInteger.ZERO);
		list_M.add(BigInteger.ONE);
		list_M.add(BigInteger.ZERO);
		list_M.add(BigInteger.ONE);
		list_M.add(BigInteger.ONE);
		list_Mod.add(BigInteger.ONE);
		list_Mod.add(BigInteger.ONE);
		list_Mod.add(BigInteger.ZERO);
		list_Mod.add(BigInteger.ONE);
		ArrayList<BigInteger> temp = test.modAddition(list_R, list_M, list_Mod, BigInteger.TWO);
		list_check.add(BigInteger.ZERO);
		list_check.add(BigInteger.ONE);
		list_check.add(BigInteger.ZERO);
		list_check.add(BigInteger.ZERO);
		list_check.add(BigInteger.ZERO);
		boolean test = true;
		for(int i = 0; i < temp.size(); i++){
			if(temp.get(i).compareTo(list_check.get(i)) != 0)
				test = false;
		}
		Assert.assertTrue(test);

	}

	@Test
	public void polynomMultiplikationTest(){
		ArrayList<BigInteger> list_R = new ArrayList<>(), list_M = new ArrayList<>(), list_Mod = new ArrayList<>(), list_check = new ArrayList<>();
		list_R.add(BigInteger.ONE);
		list_R.add(BigInteger.ZERO);
		list_R.add(BigInteger.ONE);
		list_M.add(BigInteger.ONE);
		list_M.add(BigInteger.ONE);
		list_M.add(BigInteger.ONE);
		list_Mod.add(BigInteger.ONE);
		list_Mod.add(BigInteger.ONE);
		list_Mod.add(BigInteger.ZERO);
		list_Mod.add(BigInteger.ONE);
		ArrayList<BigInteger> temp = test.modMultiply(list_R, list_M, list_Mod, BigInteger.TWO);
		list_check.add(BigInteger.ZERO);
		list_check.add(BigInteger.ONE);
		list_check.add(BigInteger.ONE);
		list_check.add(BigInteger.ZERO);
		list_check.add(BigInteger.ZERO);
		boolean test = true;
		for(int i = 0; i < temp.size(); i++){
			if(temp.get(i).compareTo(list_check.get(i)) != 0)
				test = false;
		}
		Assert.assertTrue(test);

	}
}
