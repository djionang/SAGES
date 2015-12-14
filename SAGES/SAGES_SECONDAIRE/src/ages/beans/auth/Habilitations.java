package ages.beans.auth;

import java.io.Serializable;

import entities.ItemRole;


public class Habilitations implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean create;
	private boolean update;
	private boolean delete;
	private boolean view;
	
	private ItemRole item;

	/**
	 * @return the create
	 */
	public boolean isCreate() {
		return create;
	}

	/**
	 * @param create the create to set
	 */
	public void setCreate(boolean create) {
		this.create = create;
	}

	/**
	 * @return the update
	 */
	public boolean isUpdate() {
		return update;
	}

	/**
	 * @param update the update to set
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}

	/**
	 * @return the delete
	 */
	public boolean isDelete() {
		return delete;
	}

	/**
	 * @param delete the delete to set
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	/**
	 * @return the view
	 */
	public boolean isView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(boolean view) {
		this.view = view;
	}

	/**
	 * @return the item
	 */
	public ItemRole getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(ItemRole item) {
		this.item = item;
	}
	
}
