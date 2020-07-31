/**
 * 
 */
package com.mdy.dzs.data.action.impl;

import java.util.List;

import com.mdy.dzs.data.ApplicationAwareAction;
import com.mdy.dzs.data.action.VersionAction;
import com.mdy.dzs.data.domain.version.Version;

public class VersionActionImpl extends ApplicationAwareAction implements VersionAction {

	@Override
	public List<Version> getVersions() {
		return versionAO().queryList();
	}

}
