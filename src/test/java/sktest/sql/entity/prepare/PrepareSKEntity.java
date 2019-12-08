package sktest.sql.entity.prepare;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKEntity;

import javax.persistence.*;
import java.util.Map;

@Accessors(chain = true)
@Table(schema = "sktest1_schema", name = "sktest1_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"has_length", "not_null_col"})})
@ToString
public class PrepareSKEntity extends SKEntity<Map<String, OperationContent>> {

  @Column(length = 40)
  @Getter
  @Id
  @Setter
  private String id;

  @Column(length = 1, columnDefinition = "COMMENT 'The deleted status of record {Y:Invalid,N:Valid(Default)}'")
  @Getter
  @Setter
  private String deleted;

  /**
   * @see org.shaneking.skava.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT 'The last modification time of record'")
  @Getter
  @Setter
  private String modDateTime;

  @Column(length = 40, columnDefinition = "COMMENT 'The last modified person of record'")
  @Getter
  @Setter
  private String modUserId;

  @Column(nullable = false)
  @Getter
  @Setter
  @Version
  private Integer ver;

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


}
