/*
 * 
 */
package com.evoapps.cloudapp.datastructure;

import android.text.format.Time;

// TODO: Auto-generated Javadoc
/**
 * The Class FileInfo.
 */
public class FileInfo {
		
		/** The id. */
		private int id;
		
		/** The name. */
		private String name;
		
		/** The create time. */
		private Time createTime;
		
		/** The modification time. */
		private Time modificationTime;
		
		/** The access time. */
		private Time accessTime;
		
		/** The type. */
		private int type;
		
		/** The owner. */
		private int owner;
		
		/** The is available. */
		private boolean isAvailable;
	
		/**
		 * Instantiates a new file info.
		 */
		public FileInfo(){
			
		}
		
		/**
		 * Gets the creates the time.
		 *
		 * @return the creates the time
		 */
		public Time getCreateTime() {
			return createTime;
		}
		
		/**
		 * Sets the creates the time.
		 *
		 * @param createTime the new creates the time
		 */
		public void setCreateTime(Time createTime) {
			this.createTime = createTime;
		}
		
		/**
		 * Gets the modification time.
		 *
		 * @return the modification time
		 */
		public Time getModificationTime() {
			return modificationTime;
		}
		
		/**
		 * Sets the modification time.
		 *
		 * @param modificationTime the new modification time
		 */
		public void setModificationTime(Time modificationTime) {
			this.modificationTime = modificationTime;
		}
		
		/**
		 * Gets the type.
		 *
		 * @return the type
		 */
		public int getType() {
			return type;
		}
		
		/**
		 * Sets the type.
		 *
		 * @param type the new type
		 */
		public void setType(int type) {
			this.type = type;
		}
		
		/**
		 * Gets the owner.
		 *
		 * @return the owner
		 */
		public int getOwner() {
			return owner;
		}
		
		/**
		 * Sets the owner.
		 *
		 * @param owner the new owner
		 */
		public void setOwner(int owner) {
			this.owner = owner;
		}
		
		/**
		 * Checks if is available.
		 *
		 * @return true, if is available
		 */
		public boolean isAvailable() {
			return isAvailable;
		}
		
		/**
		 * Sets the available.
		 *
		 * @param isAvailable the new available
		 */
		public void setAvailable(boolean isAvailable) {
			this.isAvailable = isAvailable;
		}

		/**
		 * Gets the name.
		 *
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * Sets the name.
		 *
		 * @param name the new name
		 */
		public void setName(String name) {
			this.name = name;
		}

}
