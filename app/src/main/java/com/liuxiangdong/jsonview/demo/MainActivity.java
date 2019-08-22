package com.liuxiangdong.jsonview.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liuxiangdong.jsonview.ConfigurationProvider;
import com.liuxiangdong.jsonview.JsonView;
import com.liuxiangdong.jsonview.entry.JsonCompoundEntry;

/**
 * A demo Activity.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonView jsonView = findViewById(R.id.json_view);
        jsonView.setConfigurationProvider(new ConfigurationProvider() {
            @Override
            public boolean shouldCollapse(JsonCompoundEntry<?> compoundEntry) {
                return compoundEntry.getDepth() != 0;
            }
        });
        jsonView.setJson("{\n" +
                "    \"name\": \"Supermarket\",\n" +
                "    \"location\": \"main street\",\n" +
                "    \"serving_hour\": 24,\n" +
                "    \"products\": [{\n" +
                "        \"name\": \"fruit\",\n" +
                "        \"price\": 3.14,\n" +
                "        \"description\": \"I'm fruit, but the price makes me wonder if I'm π.\",\n" +
                "        \"sold_out\": false,\n" +
                "        \"category\": [{\n" +
                "            \"name\": \"apple\"\n" +
                "        }, {\n" +
                "            \"name\": \"orange\"\n" +
                "        }, {\n" +
                "            \"name\": \"banana\"\n" +
                "        }, {\n" +
                "            \"name\": \"grape\"\n" +
                "        }, {\n" +
                "            \"name\": \"peach\"\n" +
                "        }, {\n" +
                "            \"name\": \"strawberry\"\n" +
                "        }, {\n" +
                "            \"name\": \"melon\"\n" +
                "        }]\n" +
                "    }, {\n" +
                "        \"name\": \"vegetable\",\n" +
                "        \"price\": 20,\n" +
                "        \"description\": \"I'm vegetable, not table, but I can be on the table.\",\n" +
                "        \"sold_out\": true,\n" +
                "        \"category\": []\n" +
                "    }, {\n" +
                "        \"name\": \"book\",\n" +
                "        \"price\": 123,\n" +
                "        \"description\": \"Yes, we even sell book.\",\n" +
                "        \"sold_out\": false,\n" +
                "        \"category\": [{\n" +
                "            \"name\": \"Learning Java\"\n" +
                "        }, {\n" +
                "            \"name\": \"Learning Android\"\n" +
                "        }, {\n" +
                "            \"name\": \"Learning Java again\"\n" +
                "        }, {\n" +
                "            \"name\": \"Learning Kotlin\"\n" +
                "        }, {\n" +
                "            \"name\": \"How to keep healthy\"\n" +
                "        }]\n" +
                "    }]\n" +
                "}");

    }
}
