package NotePackage;

public class PoliteTimedMemo extends TimedMemo {
    String DEFAULT_GREETING = "Dear", DEFAULT_CLOSING = "Yours truly,";

//    PoliteTimedMemo(){
//    }

//    PoliteTimedMemo(String name, String body, String from, String to) {
//        super(name, body, from, to);
//    }

    private PoliteTimedMemo(Builder builder) {
        super(builder.name, builder.body, builder.from, builder.to);
    }

    public static class Builder {
        private String name, body, from, to;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public PoliteTimedMemo build()  {
            return new PoliteTimedMemo(this);
        }
    }

    @Override
    public String toString() {
        return '\n' + "Date: " + today + '\n' +
                "Name: " + name + '\n' +
                DEFAULT_GREETING + " " + to + '\n' +
                body + '\n' +
                DEFAULT_CLOSING + '\n' +
                from + '\n' +
                "Note#" + noteNumber + '\n' +
                FOOTER;
    }
}
