package com.codepath.apps.restclienttemplate.models;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TweetDao_Impl implements TweetDao {
  private final RoomDatabase __db;

  public TweetDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<TweetWithUser> recentItems() {
    final String _sql = "SELECT Tweet.body AS tweet_body, Tweet.createdAt AS tweet_createdAd, Tweet.id AS tweet_id FROM Tweet INNER JOIN User ON Tweet.userId = User.id ORDER BY Tweet.createdAt DESC LIMIT 5";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "tweet_body");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "tweet_id");
      final List<TweetWithUser> _result = new ArrayList<TweetWithUser>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TweetWithUser _item;
        final Tweet _tmpTweet;
        if (! (_cursor.isNull(_cursorIndexOfBody) && _cursor.isNull(_cursorIndexOfId))) {
          _tmpTweet = new Tweet();
          _tmpTweet.body = _cursor.getString(_cursorIndexOfBody);
          _tmpTweet.id = _cursor.getLong(_cursorIndexOfId);
        }  else  {
          _tmpTweet = null;
        }
        _item = new TweetWithUser();
        _item.tweet = _tmpTweet;
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
