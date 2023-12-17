/*
 *    Copyright 2023 KPG-TB
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.kpgtb.ktools.manager.data.persister.base;

import com.github.kpgtb.ktools.manager.data.GsonAdapterManager;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.LongStringType;
import org.bukkit.Location;

import java.sql.SQLException;

public class LocationPersister extends LongStringType {
    private static final LocationPersister SINGLETON = new LocationPersister();

    public LocationPersister() {
        super(SqlType.LONG_STRING, new Class[]{Location.class});
    }

    public static LocationPersister getSingleton() {
        return SINGLETON;
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
        return GsonAdapterManager.getInstance()
                .getGson()
                .toJson(javaObject);
    }

    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) throws SQLException {
        return GsonAdapterManager.getInstance()
                .getGson()
                .fromJson((String) sqlArg, Location.class);
    }
}
