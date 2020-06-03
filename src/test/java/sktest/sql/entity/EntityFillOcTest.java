package sktest.sql.entity;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.shaneking.jackson.databind.OM3;
import org.shaneking.skava.lang.String0;
import org.shaneking.sql.Keyword0;
import org.shaneking.sql.entity.SKIdAdtVerEntity;
import org.shaneking.sql.entity.SKIdEntity;
import org.shaneking.test.SKUnit;
import sktest.sql.entity.prepare.PrepareSqlSKIdAdtVerEntity;

import static sktest.sql.entity.EntitySqlTest.SKTEST1_ID;

@Slf4j
public class EntityFillOcTest extends SKUnit {
  public static final String SKTEST1_USER_ID = "SKTEST1_USER_ID";
  private static final String SKTEST1_DATE_TIME = "2019-06-23 20:00:07";
  private PrepareSqlSKIdAdtVerEntity prepareSqlSKIdAdtVerEntity;

  @Before
  public void setUp() {
    prepareSqlSKIdAdtVerEntity = new PrepareSqlSKIdAdtVerEntity();
  }

  @Test
  public void fillOc() {
    prepareSqlSKIdAdtVerEntity = new PrepareSqlSKIdAdtVerEntity().setHasLength("hasLength").setNoGetMethod("noGetMethod").setWithoutAnnotation("withoutAnnotation").setReName("reName").setLongText("longText");
    prepareSqlSKIdAdtVerEntity.setVer(1).setInvalid(String0.N).setId(SKIdEntity.FIELD__ID);

    prepareSqlSKIdAdtVerEntity.forceHavingOc(SKIdAdtVerEntity.FIELD__MOD_DATE_TIME + "__1").setOp(Keyword0.BETWEEN).setCl(Lists.newArrayList("2019-06-23 20:00:07", "2020-01-17 20:20:01"));
    prepareSqlSKIdAdtVerEntity.forceHavingOc(SKIdAdtVerEntity.FIELD__MOD_DATE_TIME + "__2").setOp(Keyword0.LIKE).setCs("2020-01-17").setEw("%");
    prepareSqlSKIdAdtVerEntity.forceWhereOc(SKIdAdtVerEntity.FIELD__ID + "__1").setOp(Keyword0.IN).setCl(Lists.newArrayList(SKIdAdtVerEntity.FIELD__ID, SKTEST1_ID));
    prepareSqlSKIdAdtVerEntity.forceWhereOc(SKIdAdtVerEntity.FIELD__ID + "__2").setOp(String0.MORE).setCs("1579263862");

    prepareSqlSKIdAdtVerEntity.setGroupByList(Lists.newArrayList(SKIdAdtVerEntity.FIELD__INVALID));
    prepareSqlSKIdAdtVerEntity.setOrderByList(Lists.newArrayList(String0.field2DbColumn(SKIdAdtVerEntity.FIELD__MOD_DATE_TIME)));
    prepareSqlSKIdAdtVerEntity.setSelectList(Lists.newArrayList(SKIdAdtVerEntity.FIELD__INVALID, Keyword0.COUNT_1_));

    Assert.assertEquals("[\"select invalid,count(1) from sktest1_schema.sktest1_table where id=? and invalid=? and ver=? and has_length=? and re_name_col=? and long_text=? and id in (?,?) and id > ? group by invalid having mod_date_time like ? and mod_date_time between ? and ? order by mod_date_time limit 30 offset 0\",[\"id\",\"N\",1,\"hasLength\",\"reName\",\"longText\",\"id\",\"sktest1_id\",\"1579263862\",\"2020-01-17%\",\"2019-06-23 20:00:07\",\"2020-01-17 20:20:01\"]]", OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.selectSql()));
  }
}
