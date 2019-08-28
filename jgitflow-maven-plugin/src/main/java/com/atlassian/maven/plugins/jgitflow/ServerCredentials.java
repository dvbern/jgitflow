/*
 * Copyright © 2019 DV Bern AG, Switzerland
 *
 * Das vorliegende Dokument, einschliesslich aller seiner Teile, ist urheberrechtlich
 * geschützt. Jede Verwertung ist ohne Zustimmung der DV Bern AG unzulässig. Dies gilt
 * insbesondere für Vervielfältigungen, die Einspeicherung und Verarbeitung in
 * elektronischer Form. Wird das Dokument einem Kunden im Rahmen der Projektarbeit zur
 * Ansicht übergeben, ist jede weitere Verteilung durch den Kunden an Dritte untersagt.
 */

package com.atlassian.maven.plugins.jgitflow;

import org.apache.maven.settings.Server;
import org.apache.maven.settings.Settings;

public class ServerCredentials implements Credentials {
	private final String serverId;
	private final String username;
	private final String passsword;

	public ServerCredentials(String serverId, String username, String passsword) {
		this.serverId = serverId;
		this.username = username;
		this.passsword = passsword;
	}

	public static ServerCredentials parseSettings(String serverId, Settings settings) {
		Server server = settings.getServer(serverId);
		if (server == null) {
			// FIXME: gibt es schoenere Exceptions bei Maven?
			throw new IllegalArgumentException("Server not defined: " + serverId);
		}

		return new ServerCredentials(serverId, server.getUsername(), server.getPassword());
	}

	public String getServerId() {
		return serverId;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return passsword;
	}
}
