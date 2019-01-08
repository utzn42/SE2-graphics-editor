package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Serializable {@link List} of IDs for use with the {@link Serializer} class.
 */
public class IDList implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<String> idList;

  /**
   * Creates an empty IDList.
   */
  public IDList() {
    idList = new ArrayList<>();
  }

  /**
   * Creates an IDList containing the given List of IDs.
   *
   * @param idList The List of IDs to store.
   */
  public IDList(List<String> idList) {
    this.idList = idList;
  }

  /**
   * Returns the stored List of IDs.
   *
   * @return The stored List of IDs.
   */
  public List<String> getIdList() {
    return idList;
  }

  /**
   * Stores the given List of IDs in the IDList.
   *
   * @param idList The List of IDs to store.
   */
  public void setIdList(List<String> idList) {
    this.idList = idList;
  }

}
