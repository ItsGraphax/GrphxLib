package de.itsgraphax.grphxLib.utils;

import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ResourcepackSender implements Listener {
    protected final String subdir;
    protected final String id;
    protected final UUID uuid;
    protected final URI uri;
    protected String hash;

    public ResourcepackSender(String subdir, String id) {
        this.subdir = subdir;
        this.id = id;
        this.uuid = UUID.nameUUIDFromBytes((subdir + "/" + id).getBytes(StandardCharsets.UTF_8));
        this.uri = makeUri("");
    }

    public void updateHash() {
        // Get hash
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(makeUri("internal/hash/"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {
                throw new RuntimeException(String.format("Hash request to server returned %s", response.statusCode()));
            }

            this.hash = response.body();

            client.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected URI makeUri(String prefix) {
        return URI.create(String.format("https://vps.itsgraphax.de/%s%s/%s.zip", prefix, subdir, id));
    }

    @EventHandler
    void onJoin(PlayerJoinEvent e) {
        updateHash();
        e.getPlayer().sendResourcePacks(ResourcePackRequest
                .resourcePackRequest()
                .replace(false)
                .required(false)
                .prompt(Component
                        .text("This Server uses Resource Packs to enhance your experience. These resourcepacks are not required and can be manually downloaded when wanted."))
                .packs(ResourcePackInfo
                        .resourcePackInfo()
                        .uri(uri)
                        .id(uuid)
                        .hash(hash))
                .asResourcePackRequest());
    }
}
