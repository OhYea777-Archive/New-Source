package com.ohyea777.craftcast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.bukkit.Bukkit;

public class NowPlaying implements Runnable {

	private String nowPlaying;

	@Override
	public void run() {
		Bukkit.getScheduler().runTaskAsynchronously(CraftCast.getInstance(), new Runnable() {
			@Override
			public void run() {
				if (hasChanged()) {
					if (Messages.SHOULD_BROADCAST.getAsBoolean()) {
						Bukkit.broadcast(Messages.BROADCAST.sub("{Playing}", nowPlaying), "cc.broadcast");
					}
				}
			}
		});
	}

	private boolean hasChanged() {
		String s = get();

		if (!s.equals(nowPlaying)) {
			nowPlaying = s;
			CraftCast.getInstance().setNowPlaying(nowPlaying);
			return true;
		}

		return false;
	}

	private String get() {
		try {
			URL url = new URL(Messages.URL.getNoPrefix());
			BufferedReader in = new BufferedReader(new InputStreamReader(getStreamFromUrl(url), Charset.forName("UTF-8")));

			String html = readAll(in);
			String s = html.substring(html.indexOf("<body>") + 6, html.indexOf("</body>"));
			
			if (s.split(",").length >= 7) {
				return patch(Arrays.copyOfRange(s.split(","), 6, s.split(",").length));
			}
		} catch (MalformedURLException e) { }

		return "";
	}

	private InputStream getStreamFromUrl(URL urlTemp) {
		URLConnection uc;
		InputStream inputStream = null;
		URL url = null;
		try {
			url = urlTemp;
			uc = url.openConnection();
			uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0a2) Gecko/20110613 Firefox/6.0a2");
			uc.connect();
			inputStream = uc.getInputStream();
		} catch (IOException e) { }
		return inputStream;
	}

	protected String readAll(BufferedReader rd) {
		StringBuilder sb = new StringBuilder();
		int cp;
		try {
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
		} catch (IOException e) { }
		return sb.toString();
	}

	protected String patch(String... args) {
		StringBuilder string = new StringBuilder(args[0] != null ? args[0] : "");

		for (int i = 0; i < args.length; i ++) {
			if (i > 0) {
				string.append(",").append(args[i]);
			}
		}

		return string.toString();
	}

}
