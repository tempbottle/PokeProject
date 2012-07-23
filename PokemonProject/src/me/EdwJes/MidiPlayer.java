/*
 * Copyright (c) 2007 by Karl Helgason
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package me.EdwJes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.swing.SwingUtilities;
import com.sun.media.sound.AudioSynthesizer;

public class MidiPlayer{
	boolean initiated=false;
	Sequencer sequencer=null;
	Sequence midiSeq=null;
	File midiSeqFile=null;

	Soundbank soundBank = null;
	File soundBankFile = null;

	Synthesizer softsynth = null;

	Mixer synthmixer = null;
	SourceDataLine line = null;
	
	AudioFormat format;
	
	public MidiPlayer(){}
	
	/*
	 * Find available AudioSynthesizer.
	 */
	public static AudioSynthesizer findAudioSynthesizer() throws MidiUnavailableException {
		// First check if default synthesizer is AudioSynthesizer.
		Synthesizer synth = MidiSystem.getSynthesizer();
		if (synth instanceof AudioSynthesizer)
			return (AudioSynthesizer) synth;

		// If default synhtesizer is not AudioSynthesizer, check others.
		Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0; i < infos.length; i++) {
			MidiDevice dev = MidiSystem.getMidiDevice(infos[i]);
			if (dev instanceof AudioSynthesizer)
				return (AudioSynthesizer) dev;
		}

		// No AudioSynthesizer was found, return null.
		return null;
	}	

	public void init(int sampleRate,int bits,int channels,int latencyMs) {
		try {
			
			final AudioSynthesizer synth = findAudioSynthesizer();
			Map<String, Object> ainfo = new HashMap<String, Object>();

			try {

				format = new AudioFormat(sampleRate,bits,channels, true, false);

				ainfo.put("format", format);
				ainfo.put("max polyphony", 64);				
				ainfo.put("latency", latencyMs * 1000L);

				ainfo.put("interpolation", false);
				ainfo.put("large mode", true);

			} catch (Throwable t) {
				t.printStackTrace();
			}
			synth.open(line, ainfo);

			Runnable r = new Runnable() {
				public void run() {
					softsynth = synth;
					if (soundBank == null)
						soundBank = synth.getDefaultSoundbank();
					try {
						if (sequencer == null) {
							try {
								sequencer = MidiSystem.getSequencer(false);
							} catch (MidiUnavailableException e2) {
								e2.printStackTrace();
							}
						}
						if (sequencer.isOpen())
							sequencer.close();
						sequencer.getTransmitter().setReceiver(
								softsynth.getReceiver());
						sequencer.open();
					} catch (MidiUnavailableException e) {
						e.printStackTrace();
					}
					initiated=true;
				}
			};

			if (SwingUtilities.isEventDispatchThread())
				r.run();
			else
				SwingUtilities.invokeLater(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public void initMIDI_inThread() {
		synth_loaded = false;
		new Thread() {
			public void run() {
				initMIDI();
			}
		}.start();

	}*/

	public void close() {
		if (initiated) {
			sequencer.close();
			softsynth.close();
			if (line != null) {
				line.close();
				line = null;
			}
			if (synthmixer != null) {
				synthmixer.close();
				synthmixer = null;
			}
		initiated=false;
		}
	}

	public void midiLoad(File MidiSeqFile) {
		try {
			Sequence newseq = MidiSystem.getSequence(MidiSeqFile);

			midiSeq = newseq;
			midiSeqFile = MidiSeqFile;
			sequencer.stop();
			sequencer.setSequence(midiSeq);
		} catch (Throwable e) {
			System.out.println(e.toString());
		}

	}
	
	public void midiPlay(int tickPos){
		sequencer.setTickPosition(tickPos);
		midiPlay();
	}
	
	public void midiPlay(){
		sequencer.start();
	}
	
	public void midiStop(){
		sequencer.stop();
		sequencer.setTickPosition(0);
	}
	
	public void midiSetPos(long tickPos){
		sequencer.setTickPosition(tickPos);
	}
	
	public void midiPause(){
		sequencer.stop();
	}

	public void soundbankLoad(File soundbankFile) {
		try {
			FileInputStream fis = new FileInputStream(soundbankFile);
			Soundbank newsbk;
			try{				
				newsbk = MidiSystem.getSoundbank(new BufferedInputStream(fis));
			}
			finally{
				fis.close();
			}
			soundbankUnload();
			this.soundBankFile = soundbankFile;
			soundBank = newsbk;
			softsynth.loadAllInstruments(soundBank);
		} catch (Throwable e) {
			System.out.println(e.toString());
		}
	}
	
	public void soundbankUnload(){
		if (soundBank!=null){
			softsynth.unloadAllInstruments(soundBank);
			soundBank=null;
			soundBankFile=null;}
	}
}
