package com.omar.listener.app

/**
 * Created by pelangovan on 5/23/2017.
 */
class AwsData {

        private String filename;
        private String bucket;

        public String getFilename() {
            return filename;
        }

        public String getBucket() {
            return bucket;
        }

        public void setFilename(String greeting) {
            this.filename = greeting;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }
}
