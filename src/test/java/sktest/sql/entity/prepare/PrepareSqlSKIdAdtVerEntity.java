package sktest.sql.entity.prepare;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.lang.String20;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKIdAdtVerEntity;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Accessors(chain = true)
@Table(schema = "sktest1_schema", name = "sktest1_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"has_length", "not_null_col"})})
@ToString(callSuper = true)
public class PrepareSqlSKIdAdtVerEntity extends SKIdAdtVerEntity<Map<String, OperationContent>> {
  @Column(length = 10)
  @Getter
  @Setter
  private String hasLength;

  @Column
  @Setter
  private String noGetMethod;

  @Column(nullable = false)
  @Getter
  @Setter
  private String notNullCol;

  @Column(unique = true)
  @Getter
  @Setter
  private String uniqueCol;

  @Getter
  @Setter
  private String withoutAnnotation;

  @Column(name = "re_name_col")
  @Getter
  @Setter
  private String reName;

  @Column
  @Getter
  @Lob
  @Setter
  private String longText;

  @Override
  public List<OperationContent> findHavingOCs(@NonNull String fieldName) {
    Map<String, OperationContent> ocMap = this.getHavingOCs();
    if (ocMap == null) {
      ocMap = Maps.newHashMap();
      this.setHavingOCs(ocMap);
    }
    return ocMap.keySet().parallelStream().filter(s -> s != null).filter(s -> s.equals(fieldName) || s.startsWith(fieldName + String20.UNDERLINE_UNDERLINE)).map(s -> this.getHavingOCs().get(s)).collect(Collectors.toList());
  }

  @Override
  public List<OperationContent> findWhereOCs(@NonNull String fieldName) {
    Map<String, OperationContent> ocMap = this.getWhereOCs();
    if (ocMap == null) {
      ocMap = Maps.newHashMap();
      this.setWhereOCs(ocMap);
    }
    return ocMap.keySet().parallelStream().filter(s -> s != null).filter(s -> s.equals(fieldName) || s.startsWith(fieldName + String20.UNDERLINE_UNDERLINE)).map(s -> this.getWhereOCs().get(s)).collect(Collectors.toList());
  }

  public OperationContent forceHavingOc(@NonNull String field) {
    Map<String, OperationContent> ocMap = this.getHavingOCs();
    if (ocMap == null) {
      ocMap = Maps.newHashMap();
      this.setHavingOCs(ocMap);
    }
    return forceOc(ocMap, field);
  }

  public OperationContent forceOc(@NonNull Map<String, OperationContent> ocMap, @NonNull String field) {
    OperationContent oc = ocMap.get(field);
    if (oc == null) {
      oc = new OperationContent();
      ocMap.put(field, oc);
    }
    return oc;
  }

  public OperationContent forceWhereOc(@NonNull String field) {
    Map<String, OperationContent> ocMap = this.getWhereOCs();
    if (ocMap == null) {
      ocMap = Maps.newHashMap();
      this.setWhereOCs(ocMap);
    }
    return forceOc(ocMap, field);
  }
}
