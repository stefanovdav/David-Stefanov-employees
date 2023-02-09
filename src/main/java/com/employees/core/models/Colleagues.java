package com.employees.core.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Colleagues {
	private final String id;
	private final String eId1;
	private final String eId2;


	public Colleagues(String eId1, String eId2) {
		this.eId1 = eId1;
		this.eId2 = eId2;
		if (eId1.compareTo(eId2) < 0) {
			this.id = eId1 + ":" + eId2;
		} else {
			this.id = eId2 + ":" + eId1;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Colleagues that = (Colleagues) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
