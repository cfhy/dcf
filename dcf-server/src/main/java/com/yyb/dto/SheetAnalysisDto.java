package com.yyb.dto;

import com.yyb.dto.sheetanalysis.*;
import lombok.Data;

import java.util.List;

/**
 * 报表分析
 */
@Data
public class SheetAnalysisDto {
    /**
     * 第1步：准备好相关公司的年报，可以通过同花顺财经个股频道 http://stockpage.10jqka.com.cn/ 查找出公司，进入公司页面后，点击中间“财务分析”中的“财务指标”
     * 下拉找到左边菜单选择三大报表，按“年度”标签来“导出数据”
     */

    /**
     * 第2步：先看目标公司最近期的年报	可以先从“合并资产负债表”开始看起，先看2019年报上的合并资产负债表。
     */

    /**
     * 第3步：把合并资产负债表各个科目看一遍，标记异常科目。
     * 把最近期年报上合并资产负债表的每个科目快速看一遍，把占总资产比率大于3%且同比增长或下降大于30%的基础科目标记一下，注意需要同时满足这两个条件的基础科目，视为异常科目。
     * 占总资产比率小于3%的科目，出现较大的异常变动也是可以忽略不计的，因为对公司的影响是极其有限的。
     */

    /**
     * 第4步：搜索合并资产负债表异常科目，查明原因。
     * 在年报中搜索“资产及负债状况”，可以看到相关原因，并分析这种变化对于公司是有利还是有害。也可以在上面同花顺个股的“财务分析”中的“指标变动说明”中进行查看。
     * 资产项中的“应收票据、应收账款、其他应收款、预付款项、存货、在建工程、长期待摊费用”这几个科目如果出现异常变化，是需要重视的，这是财务造假的高发科目。
     * 负债项中的“短期借款、应付票据、应付账款、其他应付款”这几个科目如果出现异常变化，也是需要重视的，偿债危机往往是从这几个科目开始的。"
     */

    /**
     *  第5步：看总资产，判断公司实力及扩张能力。
     *  5.1、总资产金额大的公司实力更强，总资产同比增长快的公司扩张能力更强。
     *  5.2、一般情况下，一家公司的总资产规模在一定程度上能反映这家公司在行业中的地位。总资产规模排名第一的公司一般就是这个行业中的老大。
     *  5.3、同比增长较快，公司可能处于快速扩张之中，但也可能来自债务。同比负增长，公司很可能处于收缩或者衰退之中。
     */
    private List<TotalAssetsDto> totalAssetsList;

    /**
     * 第6步：看资产负债率，判断公司的债务风险。
     * 比率越小，公司未来发生债务危机的可能较小；比率越大，公司未来发生债务危机的可能较大；一般同比大幅增加的公司，遇到问题的概率比较大，偿债风险较大。
     * 资产负债率并不是越低越好，要知道像应付票据、应付账款、预收款项之类的负债高恰恰是公司竞争力强的表现。
     *
     * 资产负债率大于60%的公司，债务风险较大，淘汰掉。
     */
    private List<AssetsLiabRatioDto> assetsLiabRatioList;

    /**
     * 第7步：看有息负债和准货币资金，排除偿债风险。
     * 大于0，无偿债风险。 异常情况：准货币资金和短期借款或长期借款的金额都很大，很可能企业实际没有钱，后期风险很大。
     * 小于0，有偿债风险。
     *
     * 对于资产负债率大于40%的公司， 我们要看他的准货币资金是否大于有息负债，准货币资金小于有息负债的公司淘汰！
     */
    private List<LiabDto> liabList;

    /**
     * 第8步：看应收应付和预付预收，判断公司的行业地位。
     * 应付预收属于公司的“负债”，占用别人的资金。金额越大，代表公司的竞争力越强，行业地位越高。
     * 应收预付属于公司的“资产”，资金被别人占用了。 金额越小，代表公司的竞争力越强，行业地位越高。
     * 应付预收与应收预付之差大于0，公司无偿占用上游供应商和下游经销商的资金，竞争力强。小于0，公司的自有资金被其他公司无偿占用，竞争力相对较弱。
     * 应收账款的规模较大，经营风险自然也较大。比率大于20%的公司淘汰。
     */
    private List<ArBrApBpDto> arBrApBpList;

    /**
     * 第9步：看固定资产，判断公司的轻重。
     * “在建工程”正常情况下后期会转为“固定资产”，工程物资”正常情况下后期转为“在建工程”
     * 比率大于40%，重资产型公司。维持竞争力的成本比较高，风险相对较大。小于40%，轻资产型公司。保持持续的竞争力成本相对要低一些。
     * 40%只是经验值，不是说重资产型公司就不能投，重资产型公司不代表盈利能力不好，比如一家持续专注于主业的公司，其获得持续成功的概率就会大一些。
     * 当我们遇到重资产型公司，安全起见还是淘汰。
     */
    private List<FixedAssetDto> fixedAssetList;

    /**
     * 第10步：看投资类资产，判断公司的专注程度。
     * 比率大于10%，不够专业，淘汰！小于10%，优秀的公司一定是专注于主业的公司，与主业无关的投资类资产占总资产的比例应当很低才对，最好为0。
     * 经验： 一家非常专注于主业的公司，在未来持续保持竞争优秀的概率较大。
     */
    private List<InvestmentAssetsDto> investmentAssetsList;

    /**
     * 第11步：把合并利润表和合并现金流量表各个科目看一遍，标记异常科目。
     * 先把“合并利润表”中占总资产比率大于3%且增降幅度大于30%的异常科目标记出来，再把“合并现金流量表”中占总资产比率大于3%且增降幅度大于30%的异常科目。"
     */

    /**
     * 第12步：搜索利润表和现金流量表异常科目，查明原因。
     * "在年报中搜索“经营活动现金流入”找到“现金流”相关数据，可以看到里面的变化比率及底下相关原因说明，并分析这种变化对于公司是有利还是有害。
     * 也可以在上面同花顺个股的“财务分析”中的“指标变动说明”中进行查看。
     */

    /**
     * 第13步：看营业收入，判断公司的行业地位及成长能力。
     * 1、通过营业收入的金额和含金量看公司的行业地位：营业收入金额较大 且 “销售商品、提供劳务收到的现金”与“营业收入”的比率大于110%的公司行业地位高，产品竞争力强。
     * 2、通过营业收入增长率看公司的成长能力： 营业收入增长率大于10%的公司，成长性较好。
     * 3、“销售商品、提供劳务收到的现金”与“营业收入”的比率小于100%的公司，营业收入增长率小于10%的公司，淘汰掉！
     */
    private List<IncomeDto> incomeList;

    /**
     * 第14步：看毛利率，判断公司产品的竞争力。
     * 毛利率大于40%，公司都有某种核心竞争力；小于40%，公司一般面临的竞争压力都较大，风险也较大；
     * 经验：1、低毛利率的公司要想获得成功需要比高毛利率的公司付出更大的代价，这就增加了公司的风险。2、把毛利率小于40%的公司淘汰掉。
     * 在实践中，很多公司的财务造假就是在毛利率上暴露的。假如行业地位一般的公司毛利率比行业第一名公司的毛利率还高，那么这家公司很可能在造假。
     */
    private List<GrossSellingRateDto> grossSellingRateList;

    /**
     * 第15步：看费用率，判断公司成本管控能力。
     *
     * 2018年，新会计准则，研发费用从管理费用里单设科目。
     * 当“财务费用”是负数的时候，就不把“财务费用”计算在内。"如果“财务费用”是正数，就把“财务费用”计算在内。
     * 毛利率高、费用率低，净利润率才可能高，经营成果才可能好。优秀公司的费用率与毛利率之比一般小于40%。把费用率与毛利率的比率大于60%的公司淘汰掉。"
     */
    private List<FeeRatioDto> feeRatioList;

    /**
     * 第16步：看主营利润，判断公司的盈利能力及利润质量。
     * 主营利润 = 营业收入 - 营业成本 - 税金及附加 - 四费合计
     * 主营利润率 = 主营利润 / 营业收入 * 100%
     * 主营利润率:大于15%，说明主营盈利还可以。小于15%，公司不具备持续的竞争力，淘汰。
     * 主营利润占利润总额的比率,大于80%，说明“利润总额”中的绝大部分利润是由主业创造的。小于80%，这样的公司不具备持续的竞争力，淘汰。
     */
    private List<MainProfitDto> mainProfitList;

    /**
     * 第17步：看净利润，判断公司的经营成果及含金量。
     * 净利润金额越大越好，净利润小于0的公司淘汰。过去5年的“净利润现金比率”小于100%的公司，淘汰。
     * 即（过去5年的“经营活动产生的现金流量净额”总和 / 过去5年的“净利润”总和 ）， 比率小于100%的公司，淘汰掉。
     * 优秀的公司不但净利润金额大而且含金量高。优秀公司的“净利润现金比率”会持续的大于100%。
     *
     * 净利润现金比率 = 经营活动产生的现金流量净额 / 净利润 * 100%， 比率小于100%的公司，淘汰。优秀公司的“净利润现金比率”会持续的大于100%。
     */
    private List<NetProfitDto> netProfitList;

    /**
     * 第18步：看归母净利润，判断公司自有资本的获利能力。
     *
     * 归母净利润增长率：用“归母净利润”和“归母股东权益”可以计算出公司的净资产收益率，也叫ROE， 优秀公司的ROE一般会持续大于15%
     *
     * 归母净利润增长率 = （本年归属于母公司股东的净利润 - 上年归属于母公司股东的净利润）/ 上年归属于母公司股东的净利润 * 100%
     * 大于10%，说明公司在以较快的速度成长。大于10%且规模比较大，说明公司不但盈利能力强而且还在快速成长。小于0，很可能意味公司已经处于衰落之中。
     * 经验： 一般会把归母净利润增长率小于10%的公司淘汰掉。
     */
    private List<NetProfitAtsopcDto> netProfitAtsopcList;

    /**
     * 第19步：看经营活动产生的现金流量净额，判断公司的造血能力。
     * 经营活动产生的现金流量净额越大，公司的造血能力越强。
     * 差额 = 经营活动产生的现金流量净额 -（固定资产折旧 + 无形资产摊销 + 借款利息 + 现金股利）
     * 一家公司要想长期健康的发展，上面的差额要大于0，公司才有多余的资金扩大再生产。对于持续差额小于0的公司，淘汰掉！
     */
    private List<NcfFromOaDto> ncfFromOaList;

    /**
     * 第20步：看购建固定资产、无形资产和其他长期资产支付的现金，判断公司未来的成长能力。
     * “购建固定资产、无形资产和其他长期资产支付的现金”金额越大，公司未来成长能力越强。
     *  成长能力较强的公司，“购建固定资产、无形资产和其他长期资产支付的现金”与“经营活动现金流量净额”的比率一般在10%-60%之间。连续2年高于100%或低于10%的公司，淘汰掉。
     */
    private List<FutureGroupDto> futureGroupList;

    /**
     * 第21步：看分红，判断公司的品质。
     *
     * 大于30%，公司对股民还是比较慷慨的。大于70%的分红大都无法持续，一般会回归到70%以下。连续高分红的公司，财务造假的概率很小。
     * 小于30%，分红比率远低于30%的公司要么是能力有问题，要么是品质有问题。这样的公司财务造假的概率也相对较大。淘汰掉！。
     */
    private List<BonusDto> bonusList;

    /**
     * 第22步：看三大活动现金流量净额的组合类型，选出最佳类型的公司。
     * 一般只选择“正负负”或“正正负”这两种类型的公司
     */
    private List<TypeDto> typeList;

    /**
     * 第23步：看现金及现金等价物的净增加额，判断公司的稳定性。
     * 优秀的公司现金净增加额一般都是大于0的，公司所属的“正负负”或“正正负”类型才能持续的保持。小于0的公司淘汰掉。
     */
    private List<CashAddDto> cashAddList;







}