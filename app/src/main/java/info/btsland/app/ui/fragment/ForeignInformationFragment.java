package info.btsland.app.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import info.btsland.app.Adapter.NewsAdapter;
import info.btsland.app.R;
import info.btsland.app.model.News;
import info.btsland.app.ui.activity.NewsContentActivity;

public class ForeignInformationFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsAdapter adapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //初始化新闻数据
        newsList = getNews();
//        adapter = new NewsAdapter(activity, R.layout.activity_newsitem, newsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载fragment_newstitle.xml 布局
        View view = inflater.inflate(R.layout.fragment_foreign_information, container, false);
        //得到ListView的实例
        newsTitleListView = (ListView) view.findViewById(R.id.news_foreign_view);
        //启动ListView的适配器，这样ListView就能与适配器的数据相关联了
        newsTitleListView.setAdapter(adapter);
        //为ListView中的子项设置监听器
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    //ListView子项目的点击事件
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        //如果是单页模式（手机），就启动一个新的活动去显示新闻内容。
        Intent intent2 = new Intent(getActivity(), NewsContentActivity.class);
        intent2.putExtra("news", news);
        getActivity().startActivity(intent2);
    }


    private List<News> getNews() {
        //初始化新闻标题及内容
        List<News> newsList = new ArrayList<News>();

        News new8 = new News();
        new8.setTitle("韩国央行行长：比特币是商品而非货币，央行需加强对虚拟货币的研究");
        new8.setDate("2017-10-26");
        new8.setAuthor("Wolfie Zhao");
        new8.setContent("北京时间10月26日消息，韩国央行行长Lee Ju-yeol近日否决了比特币的货币属性，而是将这类加密货币归类为一种商品。\n" +
                "\n" +
                "据汉城联合通讯社报道，Lee行长否定了接受加密货币作为法定货币的可能性。这项声明是在韩国禁止ICO后，由官方发布的针对加密货币技术的最新评估。\n" +
                "\n" +
                "“对虚拟货币进行监管是合适的，因为它们属于商品，而不是法定货币，” Lee在韩国国民议会举办的一次政府审计会议上表示。\n" +
                "\n" +
                "Bank Of Korea Governor Lee Ju Yeol At Rate Decision Meeting As Key Rate Held Steady To Gauge Budget Impact\n" +
                "\n" +
                "“就目前而言，韩国央行并不适合采取这样的行动，”他补充说。\n" +
                "今年以来，韩国交易所的交易量呈现了爆发式的增长。早在八月份时，一些国会议员主张加强监管韩国地区的加密货币交易所。上个月，该国的金融监管机构采取了引人注目的监管措施，打击了ICO融资活动。\n" +
                "\n" +
                "在这次立法会议期间， Lee也承认韩国央行可更多地围绕加密货币及区块链进行研究。\n" +
                "\n" +
                "“我们还提及很多其他国家进行当中的虚拟货币研究，例如瑞典。韩国央行也将更重视对虚拟货币的研究，” Lee总结说。");
        new8.setImage(R.drawable.new2);
        newsList.add(new8);

        News new1 = new News();
        new1.setTitle("区块链和加密货币的颠覆潜力不可避免");
        new1.setDate("2017-10-26");
        new1.setAuthor("作者：Tanzeel Akhtar");
        new1.setContent("亚洲第一信用评级机构穆迪（Moody’s）投资者服务中心安慰美国支付领域，区块链和加密货币能构成的“威胁”距离我们还很“遥远”，但最终各个企业还是会采用这项技术。\n" +
                "\n" +
                "穆迪分析师斯蒂芬•索恩（Stephen Sohn）及其团队在本月中发表了一篇名为《消费者数字支付——美国》的报告，他们在报告中安慰支付领域参与者，区块链技术是一个“遥远的威胁”。\n" +
                "\n" +
                "穆迪认为，区块链技术作为一个颠覆者，将会给支付领域构成潜在的长期竞争威胁。该公司在报告中提到了一些“以技术为依托的竞争者”，他们正在改革美国的电子支付领域。\n" +
                "\n" +
                "报告指出：\n" +
                "\n" +
                "区块链技术最初是比特币这个加密货币的运作基础，目前正在考虑采用这项技术的公司可能对当前支付领域所有的参与者带来潜在威胁。区块链是由区块构成的链条式组合，区块中包含了加密信息，从而形成了一个数据库或‘账本’，最终，目前负责支付审核、清算和结算的中介平台将不再有存在的必要。\n" +
                "在当前主流支付条件受限的情况下，比特币这个加密货币是作为另一种支付系统选择而存在的，它能够移除中心化机构，不再需要他们进行交易和支付审核。除此之外，区块链技术在处理高吞吐量交易方面的能力未经证实，而当前系统需要秒级的处理能力。一旦这项技术证明其在支付领域的应用前景，我们将会看到越来越多的企业接纳它。\n" +
                "\n" +
                "区块链与金融服务业\n" +
                "\n" +
                "穆迪副总经理肖恩•琼斯（Sean Jones）及其团队在今年4月的一篇报告中认可了区块链技术除比特币以外还存在一系列“潜在应用与优势”。\n" +
                "\n" +
                "Jones提到，这项技术能够改革清算和结算领域，他还强调，区块链可以“提高交易透明度及数据安全性，同时降低单点故障”。\n" +
                "\n" +
                "区块链技术部署之后，预计交易后流程就会面临改革。不过，在对区块链技术进行投资之前还需要克服一系列阻碍。其中就包括延展性和互通性等技术问题，行业标准以及合作方式也需要尽快实现统一。\n" +
                "\n" +
                "穆迪强调，金融服务领域的监管部门对区块链基本上是持支持态度的，但至于最终他们将针对这项技术出台怎样的政策就很难说了。");
        new1.setImage(R.drawable.new5);
        newsList.add(new1);

        News new3 = new News();
        new3.setTitle("以投资者利益为先，马耳他政府出台加密货币投资基金管理规则");
        new3.setDate("2017-10-25");
        new3.setAuthor("Stan Higgins");
        new3.setContent("马耳他政府提出的加密货币投资基金规则目前正在公示阶段。\n" +
                "\n" +
                "马耳他金融服务管理局（MFSA）本周一公布了一份指南，用于指导专业投资机构招募投资者、管理风险以及进行自我管理。这份指南在下月的咨询阶段过后将面临进一步的修改，这也是马耳他政府为这项技术部署的最新的国家政策改进。\n" +
                "\n" +
                "MSFA在声明中表示，这份指南将适用于各种投资基金类型。\n" +
                "\n" +
                "MFSA正在制定一系列规则，旨在监管专业投资者基金（PIF），这类基金以虚拟货币为投资目标。MFSA目前正在考虑是否允许另类投资基金（AIF）和特定另类投资基金（NAIF，马耳他独创的投资基金类型）投资虚拟货币。\n" +
                "该国政府表示，他们将在11月10日之前接受潜在利益相关者提出的意见，之后就会审核结果并对具体的规则作出相应的调整。\n" +
                "\n" +
                "这一监管部门表示，最终他们将从投资者保护的角度制定出全新的规则。\n" +
                "\n" +
                "制定这一系列新规则的目标是在涉及虚拟货币的环境下，保护投资者的利益以及金融市场的诚信。");
        new3.setImage(R.drawable.new7);
        newsList.add(new3);

        News new2 = new News();
        new2.setTitle("银行有阻区块链创业公司发展？英国金融行为监管局（FCA）如是说");
        new2.setDate("2017-10-25");
        new2.setAuthor("Samuel Haig");
        new2.setContent("近期，英国金融行为监管局（FCA）发布了一份评估报告，指责金融机构对分布式账本技术创业公司带来了不便。\n" +
                "\n" +
                "FCA指出英国银行“批量地拒绝为某些客户提供银行账户服务”\n" +
                " \n" +
                "报告讨论了FCA建立“沙箱监管”一年来所取得的成就及经验教训，并指责了金融机构有意拒绝为区块链技术公司提供银行服务。\n" +
                "\n" +
                "FCA指出，“我们亲眼目睹了前两批沙箱计划中的不少公司遭到了银行的拒绝，对于那些分布式账本支付公司而言，尤其困难。”\n" +
                "\n" +
                "“某类型的申请公司似乎是一律拒绝”，FCA指出并补充说，\n" +
                "\n" +
                "“个别的银行中如何评估和审批服务方面，存在明显的不一致”。\n" +
                "\n" +
                "FCA表示，在一系列因素之中，银行部门拒绝向DLT公司提供服务的决定，是由“战略商业决策”驱动的。\n" +
                "\n" +
                "FCA强调消极的银行服务，将会给初生的区块链行业带来负面影响\n" +
                " \n" +
                "FCA指出，目前的银行业务和“创新”存在着竞争。\n" +
                "\n" +
                "FCA声称，“哪怕在沙箱中进行测试，如果某些公司无法拥有安全的银行账户，那么他们有可能无法满足我们的条件，就无法进入市场。”报告称：“一些企业由于被银行剥夺了获取金融服务的机会，无法按原计划进行测试。\n" +
                "\n" +
                "洗钱风险和区块链技术\n" +
                " \n" +
                "FCA称，金融机构多次以区块链技术存在洗钱风险为由，拒绝向DLT公司提供基本服务。\n" +
                "\n" +
                "FCA拒绝了这一观点，其还补充说：\n" +
                "\n" +
                "“FCA的工作，是确保英国的金融体系环境对洗钱者而言是不利的。”");
        new2.setImage(R.drawable.new1);
        newsList.add(new2);

        News new9 = new News();
        new9.setTitle("区块链即服务（BaaS）：IBM，微软与亚马逊形成三足鼎立之势");
        new9.setDate("2017-10-25");
        new9.setAuthor("Divya Joshi");
        new9.setContent("什么是区块链即服务？\n" +
                " \n" +
                "根据《区块链革命》一书的作者Don Tapscott和Alex Tapscott的定义，区块链技术是一种不会腐败的经济交易数字账本，不仅能够编写用于记录金融交易，而且还包括几乎任何有价值的东西。\n" +
                "\n" +
                "区块链是一种加密货币的所有交易的一个账本或名单，是比特币和其他加密货币的底层技术。对于区块链技术本身，它拥有无数应用，从银行服务到物联网（IoT）。\n" +
                "\n" +
                "在未来几年，Business Insider的研究服务BI Intelligence预计各个公司都会开始充实并具体化他们的区块链IoT解决方案。不过，一些企业已经推出了他们的区块链即服务。\n" +
                "\n" +
                "IBM区块链技术\n" +
                " \n" +
                "IBM Blockchain通过利用一种高度安全的，共享的和复制的账本准许企业公司将其交易工作流程数字化。IBM Blockchain是一种公共云服务，客户可用于构建安全的区块链网络。\n" +
                "\n" +
                "IBM Blockchain已经加入了由Linux基金会领导的超级账本项目（Hyperledger）来改进这种区块链的早期形式。\n" +
                "\n" +
                "IBM Blockchain平台自称是第一种完全整合的企业级区块链平台，设计用来加速多机构商业网络的发展、治理和运营。\n" +
                "\n" +
                "IBM声称他们的区块链产品是以一种高度可审计的方式建立的，可跟踪网络中发生的所有活动，让管理人员可以在出现错误时进行审计跟踪。\n" +
                "\n" +
                "微软Azure区块链技术\n" +
                " \n" +
                "微软Azure的区块链即服务声称，通过试验新的业务流程为组织机构提供了一种速度快，成本低，风险低和快速失败平台，由一个拥有行业内最大的合规投资组合的云平台提供支持。\n" +
                "\n" +
                "作为一种开放，灵活且可扩展的平台，微软Azure声称支持了越来越多的分布式账本技术，解决了在安全性，性能和运营流程方面的特殊业务和技术需求。\n" +
                "\n" +
                "他们还声称他们的智能服务，如Cortana Intelligence，能够提供不同于任何其他平台产品的独特数据管理和分析能力。\n" +
                "\n" +
                "最近，微软成为了加密货币与合约倡议（IC3）的成员。通过这个成员关系，微软预计推动企业级区块链准备就绪，并与IC3团队在密码学，博弈论、分布式系统、编程语言和系统的安全性方面进行合作。\n" +
                "\n" +
                "亚马逊AWS区块链技术\n" +
                " \n" +
                "亚马逊几乎无处不在。这个科技巨头将自己的爪子深深地伸向了全球几乎所有的经济领域，从房地产到食品再到药品，如今还有区块链技术。\n" +
                "\n" +
                "回到2016年，亚马逊云计算业务——亚马逊网络服务（AWS）与投资公司数字货币集团（DCG）达成合作为企业提供一种区块链即服务试验环境。\n" +
                "\n" +
                "双方希望通过合作提供一种服务从而使DCG投资组合中的区块链供应商能够在一种安全的环境下与客户合作，这些客户包括金融机构，保险公司，企业技术公司。\n" +
                "\n" +
                "根据AWS全球金融服务业务开发主管Scott Mullins所说，AWS正在与金融机构和区块链供应商合作刺激创新和推动无摩擦试验。\n" +
                "\n" +
                "R3 Corda区块链技术\n" +
                " \n" +
                "R3意识到分布式账本技术的力量在于其网络效应，R3因此就与这个行业合作建立了最大行业合作组织（100多家金融机构成员）——然后推出了Corda平台。\n" +
                "\n" +
                "R3 Corda是一个为金融行业打造的专业分布式账本平台，为企业提供了API和代码用于创建类区块链的应用，并且旨在现有全球金融市场创建更多高效性。\n" +
                "\n" +
                "凭借Corda，参与者能够无需中央机构的参与就能进行交易，创建了一种无摩擦的商业世界。\n" +
                "\n" +
                "Corda代表着银行，保险商，基金经理和其他参与者之间的最大共享合作，共同研究区块链技术在金融市场的应用。\n" +
                "\n" +
                "根据Coindesk报道，日本金融巨头瑞穗金融集团打算使用Corda来“实现信用证和提单发票等文件的数字化”，他们认为这个流程能够减少欺诈，增加透明性并且加强摆脱对纸质记录的依赖。\n" +
                "\n" +
                "最近，R3发布了Corda 1.0版本，这是R3联盟两年的成果，超过一半的联盟成员为代码做出了贡献。\n" +
                "\n" +
                "2017年5月份，押注分布式账本技术的大型金融机构宣布为R3区块链联盟提供1.07亿美元资金，用于全球技术发展以及将Corda企业版带给全球机构。\n" +
                "\n" +
                "区块链即服务市场趋势\n" +
                " \n" +
                "截止2017年2月份，“区块链”这个术语的搜索量已经在Gartner.com排名第二，在过去12个月增长了400%。在2015年到2016年，Gartner客户咨询数量增长了超过600%，证明了行业对这个快速发展的市场的兴趣正在日益增长。\n" +
                "\n" +
                "区块链市场到2012年的年复合增长率（CAGR）预计将达到61.5%，透明度和不可更改性都将成为区块链市场指数增长的推动因素。\n" +
                "\n" +
                "科技巨头们纷纷加入了这一行列，正在通过他们内部建立的平台和合作提供区块链即服务（BaaS）。\n" +
                "\n" +
                "区块链即服务预计将会进一步发展并成为金融技术行业最新的变革，如果你想要参与这场加密技术运动中的竞争和区块链即服务的大规模采用，那么BaaS就应该在你的雷达扫描范围内。");
        new9.setImage(R.drawable.new2);
        newsList.add(new9);

        News new6 = new News();
        new6.setTitle("英国金融管理局：分布式账本技术创业公司遭受银行打压，无法获得银行服务");
        new6.setDate("2017-10-24");
        new6.setAuthor("Nikhilesh De");
        new6.setContent("据英国金融行为监管局（FCA）上周发布的报告显示，英国分布式账本技术创业公司在获得银行服务时正遇到麻烦。\n" +
                "\n" +
                "FCA这个金融监管机构运行着一个监管沙箱，允许新类型的企业“在一种活跃的市场环境下测试创新型产品、服务和业务模式”。据报道，FCA发现其金融技术测试框架下的创业公司被阻止在银行开户。\n" +
                "\n" +
                "FCA并未透露这些创业公司的名字或者那些拒绝这些公司开户的银行名字。\n" +
                "\n" +
                "根据报道所说：\n" +
                "\n" +
                "“我们已经看到这个沙箱中的前两批企业中有很多都被银行拒绝提供服务。特别是当这些公司表明他们希望利用分布式账本技术、成为支付机构或成为电子货币机构时获得银行服务的难度更高。\n" +
                "分布式账本技术创业公司，包括直接使用比特币和其他加密货币的公司，长期以来一直存在银行服务问题。然而，事实上这些问题如今正在扩大到区块链和分布式账本公司，这个问题很值得关注，尤其是鉴于FCA是英国金融行业的监管部门，以及开放和许可的区块链系统之间的差异。\n" +
                "\n" +
                "问题更加糟糕的是，该报告指出这些创业公司被评估的标准在各银行之间是不一致的。\n" +
                "\n" +
                "FCA写道：\n" +
                "\n" +
                "“我们担心某些类型的公司的银行服务申请被银行无差别拒绝。关于他们如何运用评估标准批准银行服务的准入，一些银行之间也出现了明显矛盾。”\n" +
                "该报告继续指出一些银行担心洗钱和恐怖主义组织融资的出现以及潜在的贷款风险。\n" +
                "\n" +
                "不过，尽管面临着这些银行问题，该报告发现“这个沙箱的第一个年头满足了真正的需求”，这些结果使该机构感受到了“极大鼓舞”。");
        new6.setImage(R.drawable.new4);
        newsList.add(new6);

        News new5 = new News();
        new5.setTitle("瑞士信贷银行银行家：只有天空才是区块链的极限，比特币只是区块链的第一种杀手APP");
        new5.setDate("2017-10-23");
        new5.setAuthor("Rebecca Campbell");
        new5.setContent("尽管很多银行都拒绝比特币这种概念，不过很多银行都正在将他们的注意力转移到区块链的使用。\n" +
                "\n" +
                "根据欧洲银行瑞士信贷银行（Credit Suisse）的一位投资银行家所说，“只有天空才是区块链技术的极限”。\n" +
                "\n" +
                "瑞士信贷银行软件投资银行服务全球主管James Disney所说，就现在而言，私人股权交易的结算需要20到30天时间才能完成，然而使用区块链技术则可以缩短到数分钟完成。\n" +
                "\n" +
                "Disney在CNBC的“Fast Money”节目中表示：\n" +
                "\n" +
                "“如果你把我们本季度的交易量加在一起，我们能够释放数百千亿美元的资金，并将这些资金用于其他用途。”\n" +
                "瑞士信贷银行正在对分布式账本进行试验，测试未来的应用环境。\n" +
                "\n" +
                "纽约区块链技术公司Symbiont已经与瑞士信贷银行和R3区块链联盟的很多银行达成合作，共同展示这种技术如何能够用于银团贷款市场。\n" +
                "\n" +
                "这家瑞士银行还参与完成了一项智能合约区块链测试。经过从2016年10月份开始的4个月时间，大量银行和金融公司测试一种由区块链公司Axoni为OTC股权交换开发的原型。\n" +
                "\n" +
                "瑞士信贷银行似乎热衷于进一步开发这项技术，并在银行和所有地区进行大量创新。\n" +
                "\n" +
                "Disney表示：\n" +
                "\n" +
                "“区块链技术拥有简化我们的运营和金融服务的潜力。”\n" +
                "在谈到比特币时，Disney表示比特币已经成为区块链技术的“第一种杀手APP”。并将其与互联网的第一种“杀手APP”——email——进行了对比，他认为随着时间的发展，区块链将会获得更多创新。\n" +
                "\n" +
                "他补充说：\n" +
                "\n" +
                "“我认为天空才是区块链的极限。我们还处于非常非常早的阶段。”");
        new5.setImage(R.drawable.new5);
        newsList.add(new5);

        News new10 = new News();
        new10.setTitle("哈萨克斯坦拟发行以法币为支撑的国家加密货币");
        new10.setDate("2017-10-22");
        new10.setAuthor("Samuel Haig");
        new10.setContent("哈萨克斯坦阿斯塔纳国际金融中心（AIFC）宣布和Exante合作研发国家加密货币。哈萨克斯坦政府计划推出国家发行的加密货币，并有法币作支撑。\n" +
                "\n" +
                "Exante的“Stasis”平台将成为哈萨克斯坦国家加密货币的基础。这家位于马耳他的投资公司成立于2011年。\n" +
                "\n" +
                "AIFC负责人Kairat Kelimbetov表示，该机构相信“区块链和加密货币正在迈入当今主流经济环境”。Kelimbetov强调，AIFC致力于领先全球分布式账本技术（DLT）的研发和普及进程。他说：\n" +
                "\n" +
                "阿斯塔纳（哈萨克斯坦首都）的顶尖金融监管部门已经开始采取行为，为哈萨克斯坦的金融科技生态系统打下基础。我们认为AIFC能够成为区块链运作的国际中心，发展数字资产市场是我们将来的首要任务。\n" +
                " \n" +
                "区块链和加密货币博览会\n" +
                " \n" +
                "Kelimbetov近期谈到即将到来的博览会，AIFC希望提高国际上对哈萨克斯坦金融科技以及DLT行业的认识。据当地媒体报道，这位AIFC负责人指出：\n" +
                "\n" +
                "我们希望博览会举办地能够成为加密山谷或加密港口，全世界都对此很感兴趣。某些国家的央行支持这一发展方向，而另一些央行已经在密切关注（加密货币）了。\n" +
                "Kelimbetov强调，AIFC希望在促进加密货币创新和发展以及确保金融稳定性之间寻找平衡。\n" +
                "\n" +
                "美国和新加坡希望平等对待加密经济活动以及普通金融活动。在瑞士尤其如此，日本也已经支持某些加密货币成为支付方式。从这一方向来看，AIFC希望深入了解当前的发展流程，但与此同时我们也会对金融稳定性以及特定金融机构的监管负责。\n" +
                " \n" +
                "发展金融科技\n" +
                " \n" +
                "Kelimbetov说，该国领导已经授权AIFC作为新兴金融技术发展的专业中心。为了实现这一目标，AIFC不久将“加入由国际高新技术中心组成的联盟，其中就包括一些正在研究国家和地区加密货币以及区块链技术方案的大型国际银行”。\n" +
                "\n" +
                "本月早些时候，AIFC与微软签署谅解备忘录，旨在建立该国的区块链创新中心，促进DLT初创公司的创新发展。今年7月，AIFC宣布，其与德勤和Waves将为加密货币、区块链以及金融科技企业制定宽松的监管框架。");
        new10.setImage(R.drawable.new6);
        newsList.add(new10);

        return newsList;
    }
}
