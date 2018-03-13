package com.test.bean;

import java.util.Comparator;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MutableBeanComparator implements Comparator<MutableBean> {

	/** The reverse field. */
	private boolean reverse = false;
	/** The swap field. */
	private boolean swap = false;

	/**
	 * Getter for the reverse field.
	 * @return the value of the reverse field.
	 */
	public boolean getReverse() {
		return reverse;
	}

	/**
	 * Getter for the swap field.
	 * @return the value of the swap field.
	 */
	public boolean getSwap() {
		return swap;
	}

	/**
	 * Setter for the reverse field.
	 * @param reverse the reverse value to set.
	 */
	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	/**
	 * Setter for the swap field.
	 * @param swap the swap value to set.
	 */
	public void setSwap(boolean swap) {
		this.swap = swap;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append(getReverse());
		builder.append(getSwap());
		return builder.toString();
	}

	@Override
	public int compare(MutableBean compare1, MutableBean compare2) {
		CompareToBuilder builder = new CompareToBuilder();
		if (reverse) {
			if (swap) {
				builder.append(compare2.getValueInt(), compare1.getValueInt());
			} else {
				builder.append(compare1.getValueInt(), compare2.getValueInt());
			}
		} else {
			if (swap) {
				builder.append(compare2.getValueInt(), compare1.getValueInt());
			} else {
				builder.append(compare1.getValueInt(), compare2.getValueInt());
			}
		}
		return builder.toComparison();
	}
}
