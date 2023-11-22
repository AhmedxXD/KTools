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

package com.github.kpgtb.ktools.manager.resourcepack.uploader;

import com.github.kpgtb.ktools.util.url.UrlUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class OshiAtUploader implements IUploader{
    public String uploadFile(File fileToUpload) {
        try {
            String boundary = Long.toHexString(System.currentTimeMillis());

            HttpURLConnection connection = (HttpURLConnection) new URL("https://oshi.at").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            OutputStream output = connection.getOutputStream();
            FileInputStream inputStream = new FileInputStream(fileToUpload);

            output.write(("--" + boundary + "\r\n").getBytes());
            output.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileToUpload.getName() + "\"\r\n").getBytes());
            output.write(("Content-Type: " + URLConnection.guessContentTypeFromName(fileToUpload.getName()) + "\r\n").getBytes());
            output.write("\r\n".getBytes());

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            inputStream.close();

            output.write(("\r\n--" + boundary + "--\r\n").getBytes());

            int status = connection.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {

                InputStream input = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                reader.readLine();
                String response = reader.readLine().split(" ")[1];

                input.close();
                return response;

            } else {
                throw new IOException("Server returned HTTP response code: " + status + " " + connection.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean test() {
        return testFile("https://oshi.at");
    }

    @Override
    public boolean testFile(String url) {
        return UrlUtil.urlExists(url);
    }
}
