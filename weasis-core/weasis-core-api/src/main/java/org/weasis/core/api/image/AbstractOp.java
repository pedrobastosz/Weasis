/*******************************************************************************
 * Copyright (c) 2010 Nicolas Roduit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Nicolas Roduit - initial API and implementation
 ******************************************************************************/
package org.weasis.core.api.image;

import java.util.HashMap;
import java.util.Map;

import org.weasis.core.api.gui.util.JMVUtils;

public abstract class AbstractOp implements ImageOpNode {

    protected HashMap<String, Object> params;

    public AbstractOp() {
        params = new HashMap<String, Object>();
    }

    @Override
    public void clearParams() {
        params.clear();
    }

    @Override
    public AbstractOp clone() throws CloneNotSupportedException {
        AbstractOp obj = (AbstractOp) super.clone();
        obj.params = new HashMap<String, Object>(params);
        obj.clearIOCache();
        return obj;
    }

    @Override
    public void clearIOCache() {
        for (String key : params.keySet()) {
            if (key.startsWith("op.input") || key.startsWith("op.output")) {
                params.put(key, null);
            }
        }
    }

    @Override
    public Object getParam(String key) {
        if (key == null) {
            return null;
        }
        return params.get(key);
    }

    @Override
    public void setParam(String key, Object value) {
        if (key != null) {
            params.put(key, value);
        }
    }

    @Override
    public void setAllParameters(Map<String, Object> map) {
        if (map != null) {
            params.putAll(map);
        }
    }

    @Override
    public boolean isEnabled() {
        return JMVUtils.getNULLtoTrue(params.get(ENABLE));
    }

    @Override
    public void setEnabled(boolean enabled) {
        params.put(ENABLE, enabled);
    }

    @Override
    public String getName() {
        return (String) params.get(NAME);
    }

    @Override
    public void setName(String name) {
        if (name != null) {
            params.put(NAME, name);
        }
    }

    @Override
    public void handleImageOpEvent(ImageOpEvent event) {
    }

}
