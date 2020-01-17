package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Transient;

@Accessors(chain = true)
@ToString
public abstract class SKTreeIdEntity<J> extends SKIdEntity<J> {
  @Transient
  public static final String FIELD__NODE_DESC = "nodeDesc";
  @Transient
  public static final String FIELD__NODE_NAME = "nodeName";
  @Transient
  public static final String FIELD__NODE_PATH = "nodePath";
  @Transient
  public static final String FIELD__NODE_TYPE = "nodeType";
  @Transient
  public static final String FIELD__PARENT_ID = "parentId";

  @Transient
  public static final String NODE_TYPE__ROOT = "R";
  @Transient
  public static final String NODE_TYPE__BRANCH = "B";
  @Transient
  public static final String NODE_TYPE__LEAF = "L";

  @Column(columnDefinition = "COMMENT 'Node description'")
  @Getter
  @Setter
  private String nodeDesc;

  @Column(columnDefinition = "COMMENT 'Node name'")
  @Getter
  @Setter
  private String nodeName;

  @Column(columnDefinition = "COMMENT 'Node path, /root/xxx/yyy/zzz/parentId/id/'")
  @Getter
  @Setter
  private String nodePath;

  @Column(length = 1, columnDefinition = "COMMENT 'Node type, Root, Branch or Leaf'")
  @Getter
  @Setter
  private String nodeType;

  @Column(length = 40, columnDefinition = "COMMENT 'Parent identifies'")
  @Getter
  @Setter
  private String parentId;
}
