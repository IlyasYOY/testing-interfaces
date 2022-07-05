package com.example.testinginterfaces;

import lombok.Value;

import java.time.Duration;

@Value
public class Recording {

	float volume;
	Duration length;

	public Duration length() {
		return length;
	}

	public float volume() {
		return volume;
	}

	public boolean isClipping() {
		return volume > 0;
	}

}
